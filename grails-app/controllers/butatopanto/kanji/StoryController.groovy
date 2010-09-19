package butatopanto.kanji

import grails.plugins.springsecurity.Secured

class StoryController {

 @Secured('ROLE_USER')
  def save = {
      print "Story: "
      println params.story
      println params.kanji
  }
}
