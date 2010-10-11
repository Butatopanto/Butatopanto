package butatopanto.kanji

import butatopanto.kanji.bean.ChapterSelection
import butatopanto.kanji.bean.Review
import grails.plugins.springsecurity.Secured

class ReviewController {

  def scaffold = MasteryOfFrame
  def reviewService
  def lessonService
  def lessonProgressService
  def masteryService

  def index = {
    redirect(action: "list", params: params)
  }

  def list = {
    int max = Math.min(params.max ? params.int('max') : 20, 100)
    int offset = params.offset ? params.int('offset') : 0
    String sort = params.sort ? params.sort : 'frame.number'
    String order = params.order != 'desc' ? "Ascending" : "Descending"
    List shownFrameReviews = masteryService.listMastery(sort, order, offset, max)
    [activeFrameReviewList: shownFrameReviews, numberOfActiveFrameReviews: masteryService.getMasteryCount()]
  }

  @Secured('ROLE_USER')
  def manage = {
    createChapterSelectionIfNecessary()
    [canContinue: evaluateChapters().hasSelectedChapter()]
  }

  def addLesson = {
    int chapterNumber = params.id.toInteger()
    masteryService.activateLesson(chapterNumber)
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