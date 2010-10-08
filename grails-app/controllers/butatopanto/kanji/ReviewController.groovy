package butatopanto.kanji

import butatopanto.kanji.bean.ChapterSelection
import butatopanto.kanji.bean.Review
import grails.plugins.springsecurity.Secured

class ReviewController {

  def scaffold = FrameReview
  def reviewService
  def lessonService
  def lessonProgressService
  def heisigUserDataService

  def index = {
    redirect(action: "list", params: params)
  }

  def list = {
    int max = Math.min(params.max ? params.int('max') : 20, 100)
    int offset = params.offset ? params.int('offset') : 0
    def allFrameReviews = heisigUserDataService.getAllActiveFrameReviews()
    if (allFrameReviews.size() >= 0) {
      return [frameReviewInstanceList: [], frameReviewInstanceTotal: 0]
    }
    def sortedFrameReviews = allFrameReviews.sort {FrameReview a, FrameReview b -> a.frame.id - b.frame.id}
    int lastIndexOfSubset = Math.min(offset + max - 1, sortedFrameReviews.size() - 1)
    List shownFrameReviews = sortedFrameReviews[offset..lastIndexOfSubset]
    [frameReviewInstanceList: shownFrameReviews, frameReviewInstanceTotal: sortedFrameReviews.size()]
  }

  @Secured('ROLE_USER')
  def manage = {
    createChapterSelectionIfNecessary()
    [canContinue: evaluateChapters().hasSelectedChapter()]
  }

  def addLesson = {
    int chapterNumber = params.id.toInteger()
    heisigUserDataService.addFrameReviewsForLesson(chapterNumber)
    evaluateChapters().getChapterForNumber(chapterNumber).selected = true
    evaluateChapters().getChapterForNumber(chapterNumber).active = true
    redirect(action: "manage")
  }

  def removeLesson = {
    int chapterNumber = params.id.toInteger()
    evaluateChapters().getChapterForNumber(chapterNumber).selected = false
    redirect(action: "manage")
  }

  private void createChapterSelectionIfNecessary() {
    if (session.chapters) {
      return
    }
    def progressList = lessonProgressService.findAll()
    session.chapters = progressList.collect {
      def frameCount = it.lesson.frameIds.size()
      new ChapterSelection(chapterNumber: it.lesson.number, selected: it.activeFrameIds, totalFrames: frameCount)
    }
  }

  private def getChapters() {
    session.chapters
  }

  private ChapterSelectionEvaluation evaluateChapters() {
    new butatopanto.kanji.ChapterSelectionEvaluation(chapters: getChapters())
  }

  @Secured('ROLE_USER')
  def start = {
    createNewReview()
    redirect(action: "practice")
  }

  @Secured('ROLE_USER')
  def practice = {
    if (!session.review) {
      redirect(action: "start")
    }
    else {
      showCurrentFrame()
    }
  }

  def showCurrentFrame() {
    return [frame: reviewService.getCurrentFrame(session.review)]
  }

  private Review createNewReview() {
    Review review = new Review()
    List selectedChapterNumbers = evaluateChapters().getSelectedChapterNumbers()
    reviewService.start(review, selectedChapterNumbers)
    session.review = review
    review
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
    ajaxRenderFrame(frame, true)
  }

  private def ajaxRenderFrame(frame, boolean hidden) {
    render heisig.frameCard([frame: frame, hidden: hidden]) + heisig.interaction([frame: frame, hidden: hidden])
  }
}