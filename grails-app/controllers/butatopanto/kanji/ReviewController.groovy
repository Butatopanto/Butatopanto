package butatopanto.kanji

import grails.plugins.springsecurity.Secured

class ReviewController {

  def frameService
  def scaffold = Frame

  @Secured('ROLE_USER')
  def frame = {
    [frame: frameService.getRandomFrame()]
  }

  def ajaxReveal = {
    def frame = Frame.findById(params.id)
    render heisig.frameCard([frame: frame, hidden: false]) + heisig.interaction([frame: frame, hidden: false])
  }

  def ajaxResolve = {
    if (params.reviewCorrect == "true") {
      println "Reviewed correct"
    }
    else {
      println "Reviewed incorrect"
    }
    ajaxNext()
  }

  def ajaxNext = {
    def frame = frameService.getRandomFrame()
    render heisig.frameCard([frame: frame, hidden: true]) + heisig.interaction([frame: frame, hidden: true])
  }
}
