package butatopanto.kanji

import butatopanto.test.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class FrameReviewIntegrationTest extends GrailsJUnit4TestCase {
  UserData userData

  @Before
  public void saveFrameReview() {
    userData = new UserData(userName: "the user")
    def review = new FrameReview(frame: Frame.get(1))
    userData.addToFrameReviews(review).save(failOnError: true)
  }

  @Test
  void userDataHasOneReview() {
    assertEquals 1, FrameReview.list().size()
  }

  @Test
  void doesNoLongerExistAfterRemoval() {
    FrameReview review = deleteReview()
    assertFalse FrameReview.exists(review.id)
  }

  @Test
  void noLongerBelongsToUserDataAfterRemoval() {
    deleteReview()
    assertEquals 0, findUserData().frameReviews.size()
  }

  @Test
  void cascadesDeleteFromUserData() {
    FrameReview review = findFirstFrameReviewFromUserData()
    findUserData().delete()
    assertFalse FrameReview.exists(review.id)
  }

  private FrameReview deleteReview() {
    FrameReview review = findFirstFrameReviewFromUserData()
    findUserData().removeFromFrameReviews(review)
    review.delete()
    return review
  }

  private FrameReview findFirstFrameReviewFromUserData() {
    def reviewList = findUserData().frameReviews as List
    reviewList[0]
  }

  private UserData findUserData() {
    def id = userData.id
    return UserData.get(id)
  }
}