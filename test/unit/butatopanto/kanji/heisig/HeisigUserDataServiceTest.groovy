package butatopanto.kanji.heisig;

import butatopanto.test.*
import org.junit.Before
import org.junit.Test
import butatopanto.kanji.*
import butatopanto.security.User

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

  private UserData createUserDataWithUserName() {
    new UserData(userName: userName).save()
  }

  private def assertHasFrameReviewsSortedByMeaning(expected) {
    def userData = UserData.findByUserName(userName)
    assertEquals(expected, userData.frameReviews.collect({ it.frame.meaning }).sort())
  }
}