package butatopanto.kanji

import butatopanto.security.User
import butatopanto.test.GrailsJUnit4TestCase
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
    def frameReview = findFrameReviewForFrameIdOne()
    frameReview.passed = 4
    frameReview.save(failOnError: true)
    masteryService.answerRight(1)
    assertEquals(findFrameReviewForFrameIdOne().successful, 5)
  }

  @Test
  void increasesBoxOnRightAnswer() {
    masteryService.activateLesson(1)
    def frameReview = findFrameReviewForFrameIdOne()
    frameReview.box = 7
    frameReview.save(failOnError: true)
    masteryService.answerRight(1)
    assertEquals(findFrameReviewForFrameIdOne().box, 8)
  }

  @Test
  void increasesFailedCountOnWrongAnswer() {
    masteryService.activateLesson(1)
    def frameReview = findFrameReviewForFrameIdOne()
    frameReview.failed = 4
    frameReview.save(failOnError: true)
    masteryService.answerWrong(1)
    assertEquals(findFrameReviewForFrameIdOne().failed, 5)
  }

  @Test
  void resetsBoxOnWrongAnswer() {
    masteryService.activateLesson(1)
    def frameReview = findFrameReviewForFrameIdOne()
    frameReview.box = 7
    frameReview.save(failOnError: true)
    masteryService.answerWrong(1)
    assertEquals(findFrameReviewForFrameIdOne().box, 1)
  }

  private def findFrameReviewForFrameIdOne() {
    return masteryService.findMasteryByFrameId(1)
  }
}