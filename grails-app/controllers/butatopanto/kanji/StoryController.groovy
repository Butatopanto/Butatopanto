package butatopanto.kanji

import grails.plugins.springsecurity.Secured
import butatopanto.security.User

class StoryController {

  def userService

 @Secured('ROLE_USER')
  def save = {
      User user = userService.getCurrentUser()
      def story = params.story
      def kanji = params.kanji
      new Story(user: user, text: story, kanji: kanji).save()
  }
}