package butatopanto.kanji

import grails.test.GrailsUnitTestCase
import static org.mockito.Mockito.mock
import butatopanto.kanji.bean.Review

class StartChaptersOnReviewServiceWithoutFramesTest extends GrailsUnitTestCase {

  private ReviewService reviewService = new ReviewService()
  private Random random = mock(Random)
  private Review review

  protected void setUp() {
    super.setUp()
    mockDomain Frame
    reviewService.random = random
    reviewService.masteryService = new TestMasteryService()
    review = reviewService.startChapters([])
  }

  void testHasNoCurrentFrame() {
    assertNull reviewService.getCurrentFrame(review)
  }

  void testHasNoCurrentFrameAfterResolve() {
    reviewService.resolve(review, true)
    assertNull reviewService.getCurrentFrame(review)
  }

  void testHasNoTotalFrame() {
    assertEquals 0, review.totalCount
  }

  void testStartsWithTotalFrameCountForRemainingCount() {
    assertEquals 0, review.remainingCount
  }
}
