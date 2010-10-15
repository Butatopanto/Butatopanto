package butatopanto.kanji;

import butatopanto.test.*
import org.junit.*

class StartDueOnReviewControllerTest extends GrailsJUnit4ControllerTestCase {

  StartDueOnReviewControllerTest() {
    super(ReviewController)
  }
  
  def startedReview = null

  @Before
  void configureReviewServiceAndStartDue() {
    controller.reviewService = [startDue: {review ->
         startedReview = review
    }]
    controller.startDue()
  }

  @Test
  void addsReviewToSession() {
    assertNotNull(controller.session.review)
  }

  @Test
  void redirectsToPractice() {
    assertEquals "practice", controller.redirectArgs.action
  }

  @Test
  void startsDueOnReviewService() {
    assertEquals(controller.session.review, startedReview)
  }
}