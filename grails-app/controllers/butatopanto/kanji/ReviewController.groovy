package butatopanto.kanji

import butatopanto.learning.Review
import grails.plugins.springsecurity.Secured

class ReviewController {

  def reviewService
  def chapterService
  def chapterProgressService
  def masteryService

  @Secured('ROLE_USER')
  def manage = {
    createChapterSelectionIfNecessary()
    [chaptersSelected: evaluateChapters().hasSelectedChapter(), kanjiDue: masteryService.listDueFrameIds() as boolean]
  }

  def addLesson = {
    int chapterNumber = params.id.toInteger()
    masteryService.activateLesson(chapterNumber)
    evaluateChapters().getChapterForNumber(chapterNumber).selected = true
    evaluateChapters().getChapterForNumber(chapterNumber).active = true
    updateDueCountIfNecessary(chapterNumber)
    redirect(action: "manage")
  }

  def removeLesson = {
    int chapterNumber = params.id.toInteger()
    evaluateChapters().getChapterForNumber(chapterNumber).selected = false
    redirect(action: "manage")
  }

  @Secured('ROLE_USER')
  def startSelectedChapters = {
    List selectedChapterNumbers = evaluateChapters().getSelectedChapterNumbers()
    session.review = reviewService.startChapters(selectedChapterNumbers)
    redirect(action: "practice")
  }

  @Secured('ROLE_USER')
  def startDue = {
    session.review = reviewService.startDue()
    redirect(action: "practice")
  }

  @Secured('ROLE_USER')
  def practice = {
    if (!session.review) {
      redirect(action: "startDue")
    }
    else {
      showCurrentFrame()
    }
  }

  def currentStory = {
    def frameId = session.review.currentReview
    redirect(controller: "story", action: "show", id: frameId)
  }

  def showCurrentFrame() {
    return [frame: reviewService.getCurrentFrame(session.review)]
  }

  def ajaxReveal = {
    def frame = Frame.findById(params.id)
    ajaxRenderFrame(frame, false)
  }

  def ajaxResolve = {
    boolean reviewCorrect = params.reviewCorrect == "true"
    Review review = session.review
    reviewService.resolve(review, reviewCorrect)
    def frame = reviewService.getCurrentFrame(review)
    if (frame) {
      updateDueCountIfNecessary(frame.chapter)
      ajaxRenderFrame(frame, true)
    }
    else {
      endReview()
    }
  }

  private def endReview() {
    session.review = null;
    render "<h1>Herzlichen Glückwunsch</h1>"
  }

  private void createChapterSelectionIfNecessary() {
    if (!session.chapters) {
      session.chapters = createChapterSelections()
    }
  }

  private void updateDueCountIfNecessary(def chapterNumber) {
    if (session.chapters) {
      def dueFrames = masteryService.listDueFrameIdsForChapter(chapterNumber)
      def chapterSelection = evaluateChapters().getChapterForNumber(chapterNumber)
      chapterSelection.dueFrameCount = dueFrames.size()
    }
  }

  private List createChapterSelections() {
    def progressList = chapterProgressService.findAll()
    def chapterSelection = progressList.collect {
      transformToChapterSelection(it)
    }
    return chapterSelection
  }

  private ChapterSelection transformToChapterSelection(ChapterProgress progress) {
    def frameCount = progress.chapter.frameIds.size()
    def dueCount = progress.dueFrameIds.size()
    boolean active = progress.activeFrameIds
    def chapterNumber = progress.chapter.number
    new ChapterSelection(chapterNumber: chapterNumber, selected: active, active: active, totalFrames: frameCount, dueFrameCount: dueCount)
  }

  private def getChapters() {
    session.chapters
  }

  private ChapterSelectionEvaluation evaluateChapters() {
    new butatopanto.kanji.ChapterSelectionEvaluation(chapters: getChapters())
  }

  private def ajaxRenderFrame(frame, boolean hidden) {
    render heisig.frameCard([frame: frame, hidden: hidden]) + heisig.interaction([frame: frame, hidden: hidden])
  }
}