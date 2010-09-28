package butatopanto.kanji;


import butatopanto.security.User
import butatopanto.test.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class HeisigUserDataServiceReviewRemovalTest extends GrailsJUnit4TestCase {

  private HeisigUserDataService service = new HeisigUserDataService()
  private def userName = "the user"
  butatopanto.kanji.UserData userData

  @Before
  void mockUserService() {
    service.userService = [currentUser: new User(username: userName)]
  }

  @Before
  void mockDomain() {
    mockDomain Frame, [new Frame(id: 1, meaning: 'first', lesson: 1), new Frame(id: 2, meaning: 'second', lesson: 2), new Frame(id: 3, meaning: 'third', lesson: 2)]
    this.userData = new UserData(userName: userName)
    mockDomain UserData, [userData]
  }

  @Test
  void removesFrameReviewForSingleFrameFromUserData() {
    createReviews([Frame.findByLesson(1)])
    service.removeFrameReviewsForLesson(1)
    assertHasNoReviews()
  }

  @Test
  void removesFrameReviewsForMultipleFramesFromUserData() {
    createReviews(Frame.findAllByLesson(2))
    service.removeFrameReviewsForLesson(2)
    assertHasNoReviews()
  }

  @Test
  void removesFrameReviewsOnlyForGivenLesson() {
    createReviews(Frame.list())
    service.removeFrameReviewsForLesson(2)
    assertHasFrameReviewsSortedByMeaning(['first'])
  }

  private def createReviews(def frames) {
    def reviews = frames.collect {
      new FrameReview(frame: it)
    }
    mockDomain FrameReview, reviews
    reviews.each {
      userData.addToFrameReviews(it)
    }
  }

  private UserData createUserDataWithUserName() {
    new UserData(userName: userName).save()
  }

  private def assertHasFrameReviewsSortedByMeaning(expected) {
    def userData = UserData.findByUserName(userName)
    assertEquals(expected, userData.frameReviews.collect({ it.frame.meaning }).sort())
  }

  private def assertHasNoReviews() {
    assertTrue userData.frameReviews.isEmpty()
  }
}