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
  void mockHeisigTagLib() {
    controller.class.metaClass.heisig = [
      story: {arguments ->
        "Heisig: " + arguments.text
      }
    ]
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
    controller.currentStory()
    assertEquals "Heisig: The Story", controller.response.contentAsString
  }
}