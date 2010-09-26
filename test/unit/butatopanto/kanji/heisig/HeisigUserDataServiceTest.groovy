package butatopanto.kanji.heisig;


import butatopanto.kanji.Frame
import butatopanto.kanji.FrameReview
import butatopanto.kanji.UserData
import butatopanto.security.User
import butatopanto.test.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class HeisigUserDataServiceTest extends GrailsJUnit4TestCase {

  private HeisigUserDataService service = new HeisigUserDataService()
  private def userName = "the user"

  @Before
  void mockUserService() {
    service.userService = [currentUser: new User(username: userName)]
  }

  @Before
  void mockDomain() {
    mockDomain FrameReview
    mockDomain Frame, [new Frame(id: 1, meaning: 'first', lesson: 1), new Frame(id: 2, meaning: 'second', lesson: 2), new Frame(id: 3, meaning: 'third', lesson: 2)]
    mockDomain UserData
  }

  @Test
  void createsNonExistingUserDataWhenAddingFrameReviews() {
    service.addFrameReviewsForCurrentUserAndLesson(1)
    assertNotNull "No UserData found for user", UserData.findByUserName(userName)
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
  void addsFrameToExistingCurrentUserData() {
    createUserDataWithUserName()
    service.addFrameReviewsForCurrentUserAndLesson(1)
    assertHasFrameReviewsSortedByMeaning(['first'])
  }

  @Test
  void knowsExistingCurrentUserData() {
    UserData userData = createUserDataWithUserName()
    assertEquals userData.id, service.currentUserData.id
  }

  @Test
  void retainsFrameReviewsOnRepeatedAddition() {
    service.addFrameReviewsForCurrentUserAndLesson(1)
    FrameReview review = (service.currentUserData.frameReviews as List)[0]
    review.passed = 10
    review.save()
    service.addFrameReviewsForCurrentUserAndLesson(1)
    assertEquals 10, (service.currentUserData.frameReviews as List)[0].passed
  }

  private UserData createUserDataWithUserName() {
    new UserData(userName: userName).save()
  }

  private def assertHasFrameReviewsSortedByMeaning(expected) {
    def userData = UserData.findByUserName(userName)
    assertEquals(expected, userData.frameReviews.collect({ it.frame.meaning }).sort())
  }
}