package butatopanto.kanji;


import butatopanto.learning.Review
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class StartDueOnReviewControllerTest extends GrailsJUnit4ControllerTestCase {

  StartDueOnReviewControllerTest() {
    super(AssembleReviewController)
  }

  def startedReview = null

  @Before
  void configureReviewServiceAndStartDue() {
    controller.reviewService = [startDue: {
      startedReview = new Review()
    }]
    controller.startDue()
  }

  @Test
  void addsReviewToSession() {
    assertNotNull(controller.session.review)
  }

  @Test
  void redirectsToPracticeAction() {
    assertEquals "practice", controller.redirectArgs.action
  }

  @Test
  void startsDueOnReviewService() {
    assertEquals(controller.session.review, startedReview)
  }
}