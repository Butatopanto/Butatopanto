package butatopanto.kanji

import grails.plugins.springsecurity.Secured
import butatopanto.security.User

class StoryController {

  def springSecurityService

 @Secured('ROLE_USER')
  def save = {
      def authentication = springSecurityService.authentication
      String username = authentication.username
      User user = User.findByUsername(username)
      Story story = new Story(user: user, text:params.story, kanji:params.kanji)
      story.save()
  }
}
