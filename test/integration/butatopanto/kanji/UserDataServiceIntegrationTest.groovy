package butatopanto.kanji

import butatopanto.security.User
import butatopanto.test.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class UserDataServiceIntegrationTest extends GrailsJUnit4TestCase {

  def heisigUserDataService
  def springSecurityService

  @Before
  void createUser() {
    new User(username: "Test", password: "Toast").save()
  }

  @Test
  void addsReviewsToUserData() {
    springSecurityService.reauthenticate("Test", "Toast")
    heisigUserDataService.addFrameReviewsForLesson(1)
    UserData userData = UserData.findByUserName("Test")
    assertFalse userData.frameReviews.isEmpty()
  }

  @Test
  void removesReviewsFromUserData() {
    springSecurityService.reauthenticate("Test", "Toast")
    heisigUserDataService.addFrameReviewsForLesson(1)
    heisigUserDataService.removeFrameReviewsForLesson(1)
    UserData userData = UserData.findByUserName("Test")
    assertTrue userData.frameReviews.isEmpty()
  }
}