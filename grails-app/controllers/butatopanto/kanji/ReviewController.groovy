package butatopanto.kanji

import butatopanto.learning.Review
import grails.plugins.springsecurity.Secured

@Secured('ROLE_USER')
class ReviewController {

  def reviewService
  def chapterService
  def chapterProgressService
  def masteryService

  def index = {
    redirect(action: "assemble", id: 1)
  }

  def assemble = {
    createChapterSelectionIfNecessary()
    evaluateChapters().with({
      [chaptersSelected: hasChaptersSelected(), dueFrames: hasDueFrames(), dueSelected: hasDueSelected()]
    })
  }

  def addChapter = {
    int chapterNumber = params.id.toInteger()
    masteryService.activateChapter(chapterNumber)
    def chapterSelection = getChapterSelection(chapterNumber)
    chapterSelection.selected = true
    chapterSelection.active = true
    updateDueCountIfNecessary(chapterNumber)
    continueAssembly()
  }

  def removeChapter = {
    int chapterNumber = params.id.toInteger()
    getChapterSelection(chapterNumber).selected = false
    continueAssembly()
  }

  def startSelectedChapters = {
    List selectedChapterNumbers = evaluateChapters().getSelectedChapterNumbers()
    session.review = reviewService.startChapters(selectedChapterNumbers)
    startPractice()
  }

  def startDueFramesFromSelectedChapter = {
    List selectedChapterNumbers = evaluateChapters().getSelectedChapterNumbers()
    session.review = reviewService.startDueFrom(selectedChapterNumbers)
    startPractice()
  }

  def startDue = {
    session.review = reviewService.startDue()
    startPractice()
  }

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
    def frame = Frame.get(params.id)
    ajaxRenderFrame(frame, false)
  }

  def ajaxResolve = {
    boolean reviewCorrect = params.reviewCorrect == "true"
    Review review = session.review
    reviewService.resolve review, reviewCorrect
    updateDueCount review
    reviewService.toNext review
    def frame = reviewService.getCurrentFrame(review)
    if (frame) {
      ajaxRenderFrame(frame, true)
    }
    else {
      endReview()
    }
  }


  private def ajaxRenderFrame(frame, boolean hidden) {
    def practiceTablet = heisig.practiceTablet([frame: frame, hidden: hidden])
    render practiceTablet
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

  private def updateDueCount(Review review) {
    def resolvedFrameId = review.currentReview
    def frame = Frame.findByNumber(resolvedFrameId)
    updateDueCountIfNecessary frame.chapter
  }

  private void updateDueCountIfNecessary(def chapterNumber) {
    def dueFrames = masteryService.listDueFrameIdsForChapter(chapterNumber)
    def chapterSelection = evaluateChapters().getChapterForNumber(chapterNumber)
    chapterSelection.dueFrameCount = dueFrames.size()
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
    new ChapterSelection(chapterNumber: chapterNumber, selected: false, active: active, totalFrames: frameCount, dueFrameCount: dueCount)
  }

  private def continueAssembly() {
    redirect(action: "assemble")
  }

  private def startPractice() {
    redirect(action: "practice")
  }

  private def getChapters() {
    session.chapters
  }

  private def getChapterSelection(int chapterNumber) {
    return evaluateChapters().getChapterForNumber(chapterNumber)
  }

  private ChapterSelectionEvaluation evaluateChapters() {
    new ChapterSelectionEvaluation(chapters: getChapters())
  }
}