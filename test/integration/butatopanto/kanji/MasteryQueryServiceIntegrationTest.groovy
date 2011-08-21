package butatopanto.kanji;


import butatopanto.security.User
import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test

class MasteryQueryServiceIntegrationTest extends GrailsJUnit4TestCase {

  private def userName = "MasteryQueryServiceIntegrationTest"
  def masteryQueryService
  def springSecurityService

  @Test
  void hasNoMasteryWithoutCurrentUser() {
    assertEquals([], masteryQueryService.listMasteryForCurrentUser())
  }

  @Test
  void hasNoMasteryForNewUser() {
    logInUser(springSecurityService, userName)
    assertEquals([], masteryQueryService.listMasteryForCurrentUser())
  }

  @Test
  void findsMasteriesForExistingUsers() {
    logInUser(springSecurityService, userName)
    def mastery = createMasteryForUser()
    assertEquals([mastery], masteryQueryService.listMasteryForCurrentUser())
  }

  @Test
  void findsMasteriesForExistingUsersByBox() {
    logInUser(springSecurityService, userName)
    def mastery = createMasteryForUser()
    assertEquals([mastery], masteryQueryService.listMasteriesForBox(3))
  }

  @Test
  void hasNoMasteriesByBoxIfNotLoggedIn() {
    assertEquals([], masteryQueryService.listMasteriesForBox(3))
  }

  @Test
  void findsMasteriesForGivenBoxOnly() {
    logInUser(springSecurityService, userName)
    createMasteryForUser()
    assertEquals([], masteryQueryService.listMasteriesForBox(2))
  }

  private def createMasteryForUser() {
    def user = new HeisigUser(userName: userName).save(failOnError: true)
    def frame = Frame.findById(1);
    new MasteryOfFrame(user: user, frame: frame, box: 3, dueDate: new Date()).save(failOnError: true)
  }

  private void logInUser(def springSecurityService, def userName) {
    def password = "test"
    new User(username: userName, password: password).save(flush: true, failOnError: true)
    springSecurityService.reauthenticate(userName, password)
  }
}