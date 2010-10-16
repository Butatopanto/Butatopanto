package butatopanto.kanji;


import org.junit.*
import butatopanto.kanji.bean.Review
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase

class StartDueOnReviewControllerTest extends GrailsJUnit4ControllerTestCase {

  StartDueOnReviewControllerTest() {
    super(ReviewController)
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
  void redirectsToPractice() {
    assertEquals "practice", controller.redirectArgs.action
  }

  @Test
  void startsDueOnReviewService() {
    assertEquals(controller.session.review, startedReview)
  }
}