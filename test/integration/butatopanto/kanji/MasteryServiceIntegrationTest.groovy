package butatopanto.kanji

import butatopanto.security.User
import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class MasteryServiceIntegrationTest extends GrailsJUnit4TestCase {

  private def userName = "Test"
  MasteryService masteryService
  def springSecurityService

  @Before
  void createUserAndLogIn() {
    new User(username: userName, password: "Toast").save(failOnError: true)
    springSecurityService.reauthenticate(userName, "Toast")
  }

  @Test
  void addsMasteryToHeisigUser() {
    masteryService.activateLesson(1)
    HeisigUser userData = HeisigUser.findByUserName(userName)
    assertFalse userData.masteryList.isEmpty()
  }

  @Test
  void increasesPassedCountOnRightAnswer() {
    masteryService.activateLesson(1)
    def mastery = findMasteryForFrameOne()
    mastery.passed = 4
    mastery.save(failOnError: true)
    masteryService.answerRight(1)
    assertEquals(findMasteryForFrameOne().passed, 5)
  }

  @Test
  void increasesBoxOnRightAnswer() {
    masteryService.activateLesson(1)
    def mastery = findMasteryForFrameOne()
    mastery.box = 1
    mastery.save(failOnError: true)
    masteryService.answerRight(1)
    assertEquals(findMasteryForFrameOne().box, 2)
  }

  @Test
  void increasesFailedCountOnWrongAnswer() {
    masteryService.activateLesson(1)
    def mastery = findMasteryForFrameOne()
    mastery.failed = 4
    mastery.save(failOnError: true)
    masteryService.answerWrong(1)
    assertEquals(findMasteryForFrameOne().failed, 5)
  }

  @Test
  void resetsBoxOnWrongAnswer() {
    masteryService.activateLesson(1)
    def mastery = findMasteryForFrameOne()
    mastery.box = 7
    mastery.save(failOnError: true)
    masteryService.answerWrong(1)
    assertEquals(findMasteryForFrameOne().box, 1)
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

  private MasteryOfFrame findMasteryForFrameOne() {
    return masteryService.findMasteryByFrameId(1)
  }
}