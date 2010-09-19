package butatopanto.kanji

import grails.plugins.springsecurity.Secured

class StoryController {

 @Secured('ROLE_USER')
  def save = {
      println "Hallo Save Story."
  }
}
