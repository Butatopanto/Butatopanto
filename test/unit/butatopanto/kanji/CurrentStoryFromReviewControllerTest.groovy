package butatopanto.kanji

import org.junit.Test
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import butatopanto.learning.Review
import org.junit.Before;


class CurrentStoryFromReviewControllerTest extends GrailsJUnit4ControllerTestCase {
  private Review sessionReview = new Review()
  private Review demandedReview

  CurrentStoryFromReviewControllerTest() {
    super(ReviewController)
  }

  @Before
  void setReviewInSession() {
    controller.session.review = sessionReview
  }

  @Before
  void mockReviewService() {
    controller.reviewService = [getCurrentStory: {review ->
      demandedReview = review
      "The Story"
    }]
  }

  @Test
  void asksReviewServiceForStoryOfReviewInSession() {
    controller.currentStory()
    assertSame sessionReview, demandedReview
  }

  @Test
  void rendersStoryTextFromReviewServiceViaHeisigTagLib() {
    def parameters = controller.currentStory()
    assertEquals "The Story", parameters.storyText
  }
}