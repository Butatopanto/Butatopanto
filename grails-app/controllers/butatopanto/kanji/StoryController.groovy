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
      if (!storyText) {
        storyService.deleteStory frame.id
      }
      else {
        storyService.saveStory(frame.id, storyText)
      }
      //redirect(action: "show", id: frame.id)
      def targetUri = params.uriToShowAfterSave
      redirect(uri: targetUri)
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
      def targetUri = params.uriToShowAfterSave
      [storyText: storyText, frame: frame, uriToShowAfterSave: targetUri]
    }
  }

  private Frame findFrameForIdParameter() {
    if (!params.id) {
      return null
    }
    Frame.findById(params.long('id'))
  }
}