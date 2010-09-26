package butatopanto.kanji

import grails.plugins.springsecurity.Secured
import butatopanto.kanji.bean.Review

class ReviewController {

  def reviewService
  def lessonProgressService
  def heisigUserDataService
  def scaffold = Frame

  @Secured('ROLE_USER')
  def start = {
    [lessonProgress: lessonProgressService.findAll()]
  }

  @Secured('ROLE_USER')
  def frame = {
    Review review = getInitializedReview()
    [frame: reviewService.getCurrentFrame(review)]
  }

  def addLesson = {
    heisigUserDataService.addFrameReviewsForLesson(params.id)
    redirect(action: "start")
  }

  private Review getInitializedReview() {
    if (session.review) {
      return session.review
    }
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
    render bootstrap.frameCard([frame: frame, hidden: hidden]) + bootstrap.interaction([frame: frame, hidden: hidden])
  }
}