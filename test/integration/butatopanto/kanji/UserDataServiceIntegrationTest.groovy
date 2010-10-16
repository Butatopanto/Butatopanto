package butatopanto.kanji

import butatopanto.security.User
import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class UserDataServiceIntegrationTest extends GrailsJUnit4TestCase {

  MasteryService masteryService
  def springSecurityService
  
  @Before
  void createUserAndLogIn() {
    new User(username: "Test", password: "Toast").save(failOnError: true)
    springSecurityService.reauthenticate("Test", "Toast")
  }

  @Test
  void addsReviewsToUserData() {
    masteryService.activateLesson(1)
    UserData userData = UserData.findByUserName("Test")
    assertFalse userData.masteryList.isEmpty()
  }

  @Test
  void removesReviewsFromUserData() {
    masteryService.activateLesson(1)
    masteryService.deactivateLesson(1)
    UserData userData = UserData.findByUserName("Test")
    assertTrue userData.masteryList.isEmpty()
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

  private MasteryOfFrame findMasteryForFrameOne() {
    return masteryService.findMasteryByFrameId(1)
  }
}