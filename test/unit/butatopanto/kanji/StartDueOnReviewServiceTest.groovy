package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test

import org.junit.Before
import butatopanto.learning.Review

class StartDueOnReviewServiceTest extends GrailsJUnit4TestCase {

  private ReviewService service = new ReviewService()
  private Review review

  @Before
  void configureMasteryServiceAndStartDue() {
    service.masteryService = [listDueFrameIds: {[4, 7]}]
    review = service.startDue()
  }

  @Test
  void setsAllDueFrameIdsAsRemainingFrames() {
    assertEquals([4, 7], review.remainingIds)
  }

  @Test
  void setsTotalCountToNumberOfDueFrames() {
    assertEquals 2, review.totalCount
  }
}