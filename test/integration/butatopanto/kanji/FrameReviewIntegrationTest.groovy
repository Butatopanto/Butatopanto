package butatopanto.kanji

import butatopanto.security.User
import butatopanto.test.GrailsJUnit4TestCase
import org.junit.*

class FrameReviewIntegrationTest extends GrailsJUnit4TestCase {
  User user
  FrameReview review

  @Before
  public void setUp() {
    def initialUser = new User(username: "test", password: "test")
    initialUser.save()
    user = User.findByUsername("test")
    review = new FrameReview(user: user, frame: Frame.get(1)).save()
  }

  @After
  public void cleanupDatabase() {
    if (FrameReview.exists(review.id)) {
      FrameReview.get(review.id).delete()
    }
    if (User.exists(user.id)) {
      User.get(user.id).delete()
    }
  }

  @Test
  void doesExist() {
    assertTrue FrameReview.exists(review.id)
  }

  @Test
  void doesNoLongerExistAfterDelete() {
    review.delete()
    assertFalse FrameReview.exists(review.id)
  }

  @Test
  @Ignore
  void cascadesDeleteFromUser() {
    user.delete()
    assertFalse FrameReview.exists(review.id)
  }
}