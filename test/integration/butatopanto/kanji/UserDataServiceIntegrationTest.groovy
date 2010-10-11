package butatopanto.kanji

import butatopanto.security.User
import butatopanto.test.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class UserDataServiceIntegrationTest extends GrailsJUnit4TestCase {

  HeisigUserDataService heisigUserDataService
  def springSecurityService

  @Before
  void createUserAndLogIn() {
    new User(username: "Test", password: "Toast").save(failOnError: true)
    springSecurityService.reauthenticate("Test", "Toast")
  }

  @Test
  void addsReviewsToUserData() {
    heisigUserDataService.activateLesson(1)
    UserData userData = UserData.findByUserName("Test")
    assertFalse userData.masteryList.isEmpty()
  }

  @Test
  void removesReviewsFromUserData() {
    heisigUserDataService.activateLesson(1)
    heisigUserDataService.deactivateLesson(1)
    UserData userData = UserData.findByUserName("Test")
    assertTrue userData.masteryList.isEmpty()
  }

  @Test
  void increasesPassedCountOnRightAnswer() {
    heisigUserDataService.activateLesson(1)
    def frameReview = findFrameReviewForFrameIdOne()
    frameReview.passed = 4
    frameReview.save(failOnError: true)
    heisigUserDataService.answerRight(1)
    assertEquals(findFrameReviewForFrameIdOne().passed, 5)
  }

  @Test
  void increasesBoxOnRightAnswer() {
    heisigUserDataService.activateLesson(1)
    def frameReview = findFrameReviewForFrameIdOne()
    frameReview.box = 7
    frameReview.save(failOnError: true)
    heisigUserDataService.answerRight(1)
    assertEquals(findFrameReviewForFrameIdOne().box, 8)
  }

  @Test
  void increasesFailedCountOnWrongAnswer() {
    heisigUserDataService.activateLesson(1)
    def frameReview = findFrameReviewForFrameIdOne()
    frameReview.failed = 4
    frameReview.save(failOnError: true)
    heisigUserDataService.answerWrong(1)
    assertEquals(findFrameReviewForFrameIdOne().failed, 5)
  }

  @Test
  void resetsBoxOnWrongAnswer() {
    heisigUserDataService.activateLesson(1)
    def frameReview = findFrameReviewForFrameIdOne()
    frameReview.box = 7
    frameReview.save(failOnError: true)
    heisigUserDataService.answerWrong(1)
    assertEquals(findFrameReviewForFrameIdOne().box, 1)
  }

  private def findFrameReviewForFrameIdOne() {
    return heisigUserDataService.findMasteryByFrameId(1)
  }
}