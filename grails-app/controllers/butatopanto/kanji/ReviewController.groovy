package butatopanto.kanji

import butatopanto.kanji.bean.*
import grails.plugins.springsecurity.Secured

class ReviewController {

  def reviewService
  def lessonProgressService
  def heisigUserDataService
  def scaffold = Frame

  def addLesson = {
    heisigUserDataService.addFrameReviewsForLesson(params.id)
    redirect(action: "manage")
  }

  def removeLesson = {
    heisigUserDataService.removeFrameReviewsForLesson(params.id)
    redirect(action: "manage")
  }

  @Secured('ROLE_USER')
  def manage = {
    def progressList = lessonProgressService.findAll()
    def chapters = progressList.collect {
      new ChapterSelection(chapterNumber: it.lesson.number, selected: it.activeFrameIds, totalFrames: it.lesson.frameIds.size())
    }
    [chapters: chapters, canContinue: chapters.find {it.selected} != null]
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
    reviewService.start(review)
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