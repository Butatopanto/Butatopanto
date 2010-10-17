package butatopanto.kanji

import grails.plugins.springsecurity.Secured

class StoryController {

  def storyService

  @Secured('ROLE_USER')
  def show = {
    if (!params.id) {
      render "<p>No id given.</p>"
    }
    else {
      def frameId = params.int('id')
      def storyText = storyService.findStoryByFrameId(frameId)
      [storyText: storyText, frame: Frame.findById(frameId)]
    }
  }
}