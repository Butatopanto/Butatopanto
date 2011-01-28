package butatopanto.kanji

import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class ReviewControllerStartRangeTest extends GrailsJUnit4ControllerTestCase {

  ReviewControllerStartRangeTest() {
    super(ReviewController)
  }

  private TestReviewService reviewService = new TestReviewService()

  @Before
  void configureReviewService() {
    controller.reviewService = reviewService
  }

  @Test
  void activatesKanjiBeforeStartingReview() {
    def activated = []
    controller.params.from = 5
    controller.params.to = 6
    controller.masteryService = [activateRange: {int from, int to -> activated.add from; activated.add to}]
    controller.startRange()
    assertEquals([5, 6], activated)
  }
}
