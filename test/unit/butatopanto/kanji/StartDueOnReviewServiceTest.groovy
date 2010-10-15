package butatopanto.kanji;


import butatopanto.test.GrailsJUnit4TestCase
import org.junit.Test
import butatopanto.kanji.bean.Review
import org.junit.Before

class StartDueOnReviewServiceTest extends GrailsJUnit4TestCase {

  private ReviewService service = new ReviewService()
  private Review review = new Review()

  @Before
  void configureMasteryServiceAndStartDue() {
    service.masteryService = [listDueFrameIds: {[4, 7]}]
    service.startDue(review)
  }

  @Test
  void setsAllDueFrameIdsAsRemainingFrames() {
    assertEquals([4, 7], review.remainingFrames)
  }

  @Test
  void setsTotalCountToNumberOfDueFrames() {
    assertEquals 2, review.totalCount
  }
}