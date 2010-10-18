package butatopanto.kanji;

import butatopanto.sharedtest.*
import org.junit.*
import butatopanto.security.User

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
  void findsSaveMasteryById() {
    HeisigUser heisigUser = new HeisigUser(userName: userName).save(flush: true, failOnError: true)
    heisigUser.addToMasteryList(new MasteryOfFrame(frame: Frame.get(1)))
    MasteryOfFrame savedMastery = (heisigUser.masteryList as List).get(0)
    heisigUser.addToMasteryList(new MasteryOfFrame(frame: Frame.get(2)))
    MasteryOfFrame foundMastery = masteryService.findMasteryByFrameId(1)
    assertEquals savedMastery.id, foundMastery.id
  }
}