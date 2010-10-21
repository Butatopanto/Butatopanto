package butatopanto.kanji;

import butatopanto.sharedtest.*
import org.junit.*
import butatopanto.security.User

class MasteryServiceMultipleUserTest extends GrailsJUnit4TestCase {

  def masteryService
  def springSecurityService
  User firstUser
  User secondUser

  @Before
  void createUsers() {
     firstUser = new User(username: "first", password: "first").save(flush: true, failOnError: true)
     secondUser = new User(username: "second", password: "second").save(flush: true, failOnError: true)
  }

  @Test
  void activesFrameThatIsAlreadyKnownByOtherUser() {
    springSecurityService.reauthenticate(firstUser.username, firstUser.password)
    masteryService.activateChapter(1)
    springSecurityService.reauthenticate(secondUser.username, secondUser.password)
    masteryService.activateChapter(1)
    assertEquals 15, masteryService.listActiveFrameIdsForChapter(1).size()
  }
}