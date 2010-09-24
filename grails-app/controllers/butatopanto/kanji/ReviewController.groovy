package butatopanto.kanji

import grails.plugins.springsecurity.Secured

class ReviewController {

  def frameService
  def scaffold = Frame

  @Secured('ROLE_USER')
  def frame = {
    ReviewSession reviewSession = getReviewSession()
    [frame: reviewSession.getCurrentFrame()]
  }

  private ReviewSession getReviewSession() {
    if (session.reviewSession) {
      return session.reviewSession
    }
    ReviewSession reviewSession = new ReviewSession()
    reviewSession.start()
    session.reviewSession = reviewSession
    reviewSession
  }

  def ajaxReveal = {
    def frame = Frame.findById(params.id)
    ajaxRenderFrame(frame, false)
  }

  def ajaxResolve = {
    boolean reviewCorrect =  params.reviewCorrect == "true"
    getReviewSession().resolve(reviewCorrect)
    def frame = getReviewSession().getCurrentFrame()
    ajaxRenderFrame(frame, true)
  }

  private def ajaxRenderFrame(frame, boolean hidden) {
    render heisig.frameCard([frame: frame, hidden: hidden]) + heisig.interaction([frame: frame, hidden: hidden])
  }
}