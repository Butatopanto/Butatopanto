package butatopanto.kanji.heisig;

import butatopanto.test.*
import org.junit.Before
import org.junit.Test
import butatopanto.kanji.*
import butatopanto.security.User

class HeisigUserDataServiceTest extends GrailsJUnit4TestCase {

  HeisigUserDataService service = new HeisigUserDataService()

  @Before
  void mockUserService() {
    service.userService = [currentUser: new User(username: "the user")]
  }

  @Before
  void mockDomain() {
    mockDomain FrameReview
    mockDomain Frame, [new Frame(meaning: 'first', lesson: 1), new Frame(meaning: 'second', lesson: 2), new Frame(meaning: 'third', lesson: 2)]
    mockDomain UserData
  }

  @Test
  void createsNonExistingUserDataWhenAddingFrameReviews() {
    service.addFrameReviewsForCurrentUserAndLesson(1)
    assertNotNull "No UserData found for user", UserData.findByUserName("the user")
  }

  @Test
  void addsFrameReviewForSingleFrameToUserData() {
    service.addFrameReviewsForCurrentUserAndLesson(1)
    assertHasFrameReviewsSortedByMeaning(['first'])
  }

  @Test
  void addsFrameReviewsForMultipleFramesToUserData() {
    service.addFrameReviewsForCurrentUserAndLesson(2)
    assertHasFrameReviewsSortedByMeaning(['second', 'third'])
  }

  @Test
  void addsFrameToExistingUserData() {
    new UserData(userName: "the user").save()
    service.addFrameReviewsForCurrentUserAndLesson(1)
    assertHasFrameReviewsSortedByMeaning(['first'])
  }

  private def assertHasFrameReviewsSortedByMeaning(expected) {
    def userData = UserData.findByUserName("the user")
    assertEquals(expected, userData.frameReviews.collect({ it.frame.meaning }).sort())
  }
}