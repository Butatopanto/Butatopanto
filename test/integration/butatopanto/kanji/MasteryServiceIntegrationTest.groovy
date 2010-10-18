package butatopanto.kanji;


import butatopanto.security.User
import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class MasteryServiceIntegrationTest extends GrailsJUnit4TestCase {

  private def userName = "MasteryServiceIntegrationTest"
  def masteryService
  def springSecurityService

  @Before
  void logInTestUser() {
    def password = "test"
    new User(username: userName, password: password).save(flush: true, failOnError: true)
    springSecurityService.reauthenticate(userName, password)
  }

  @Test
  void hasNoActiveFramesWithoutCurrentUserData() {
    assertEquals([], masteryService.listActiveFrameIdsForChapter(1))
  }

  @Test
  void findsSaveMasteryById() {
    HeisigUser heisigUser = new HeisigUser(userName: userName).save(flush: true, failOnError: true)
    heisigUser.addToMasteryList(new MasteryOfFrame(frame: Frame.get(1)))
    MasteryOfFrame savedMastery = (heisigUser.masteryList as List).get(0)
    heisigUser.addToMasteryList(new MasteryOfFrame(frame: Frame.get(2)))
    MasteryOfFrame foundMastery = masteryService.findMasteryByFrameId(1)
    assertEquals savedMastery.id, foundMastery.id
  }
}