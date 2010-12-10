package butatopanto.kanji

import grails.plugins.springsecurity.Secured

class StoryController {

  def storyService

  def index = {
    //nothing to do
  }

  def unknown = {
    // nothing to do
  }

  @Secured('ROLE_USER')
  def save = {
    Frame frame = findFrameForIdParameter()
    if (!frame) {
      redirect(action: "unknown")
    }
    else {
      def storyText = params.storyText
      storyService.saveStory(frame.id, storyText)
      redirect(action: "show", id: frame.id)
    }
  }

  @Secured('ROLE_USER')
  def show = {
    Frame frame = findFrameForIdParameter()
    if (!frame) {
      redirect(action: "unknown")
    }
    else {
      def storyText = storyService.findStoryTextByFrameId(frame.id)
      [storyText: storyText, frame: frame]
    }
  }

  private Frame findFrameForIdParameter() {
    if (!params.id) {
      return null
    }
    Frame.findById(params.long('id'))
  }
}