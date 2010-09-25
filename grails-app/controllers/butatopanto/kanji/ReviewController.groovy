package butatopanto.kanji

import grails.plugins.springsecurity.Secured

class ReviewController {

  ReviewService reviewService
  def scaffold = Frame

  @Secured('ROLE_USER')
  def frame = {
    Review review = getReview()
    [frame: reviewService.getCurrentFrame(review)]
  }

  private Review getReview() {
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
    boolean reviewCorrect =  params.reviewCorrect == "true"
    Review review = getReview()
    reviewService.resolve(review, reviewCorrect)
    def frame = reviewService.getCurrentFrame(review)
    ajaxRenderFrame(frame, true)
  }

  private def ajaxRenderFrame(frame, boolean hidden) {
    render heisig.frameCard([frame: frame, hidden: hidden]) + heisig.interaction([frame: frame, hidden: hidden])
  }
}