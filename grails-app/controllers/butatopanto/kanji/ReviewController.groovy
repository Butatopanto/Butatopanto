package butatopanto.kanji

import butatopanto.kanji.bean.ChapterSelection
import butatopanto.kanji.bean.LessonProgress
import butatopanto.kanji.bean.Review
import grails.plugins.springsecurity.Secured

class ReviewController {

  def scaffold = MasteryOfFrame
  def reviewService
  def lessonService
  def lessonProgressService
  def masteryService

  @Secured('ROLE_USER')
  def manage = {
    createChapterSelectionIfNecessary()
    [chaptersSelected: evaluateChapters().hasSelectedChapter()]
  }

  def addLesson = {
    int chapterNumber = params.id.toInteger()
    masteryService.activateLesson(chapterNumber)
    evaluateChapters().getChapterForNumber(chapterNumber).selected = true
    evaluateChapters().getChapterForNumber(chapterNumber).active = true
    updateDueCountIfNecessary()
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
    updateDueCountIfNecessary()
    def frame = reviewService.getCurrentFrame(review)
    if (!frame) {
      render "<h1>Herzlichen Glückwunsch</h1>"
      return
    }
    ajaxRenderFrame(frame, true)
  }

  private void createChapterSelectionIfNecessary() {
    if (!session.chapters) {
      session.chapters = createChapterSelections()
    }
  }

  private void updateDueCountIfNecessary() {
    if (session.chapters) {
      session.chapters.each {
        def chapterNumber = it.chapterNumber
        def dueFrames = masteryService.listDueFrameIdsForChapter(chapterNumber)
        it.dueFrameCount = dueFrames.size()
      }
    }
  }

  private List createChapterSelections() {
    def progressList = lessonProgressService.findAll()
    def chapterSelection = progressList.collect {
      transformToChapterSelection(it)
    }
    return chapterSelection
  }

  private ChapterSelection transformToChapterSelection(LessonProgress progress) {
    def frameCount = progress.lesson.frameIds.size()
    def dueCount = progress.dueFrameIds.size()
    boolean active = progress.activeFrameIds
    def chapterNumber = progress.lesson.number
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