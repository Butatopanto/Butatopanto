package butatopanto.kanji

import grails.plugins.springsecurity.Secured

class FrameController {

  def frameService
  def scaffold = Frame

  @Secured('ROLE_USER')      
  def train = {
    [frame: frameService.getRandomFrame()]
  }

  def next = {
    def frame = frameService.getRandomFrame()
    render heisig.frameCard([frame: frame, hidden: true]) + heisig.interaction([frame: frame, hidden: true]) 
  }

  def reveal = {
    def frame = Frame.findById(params.id)
    render heisig.frameCard([frame: frame, hidden: false]) + heisig.interaction([frame: frame, hidden: false])
  }
}
