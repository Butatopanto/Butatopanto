package butatopanto.kanji;

import butatopanto.sharedtest.*
import org.junit.*
import butatopanto.security.User

class MasteryQueryServiceIntegrationTest extends GrailsJUnit4TestCase {

  private def userName = "MasteryQueryServiceIntegrationTest"
  def masteryQueryService
  def springSecurityService

  @Test
  void hasNoMasteryWithoutCurrentUser() {
    assertEquals([], masteryQueryService.listMastery())
  }

  @Test
  void hasNoMasteryForNewUser() {
    logInUser(springSecurityService, userName)
    assertEquals([], masteryQueryService.listMastery())
  }

  private void logInUser(def springSecurityService, def userName) {
    def password = "test"
    new User(username: userName, password: password).save(flush: true, failOnError: true)
    springSecurityService.reauthenticate(userName, password)
  }
}