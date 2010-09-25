package butatopanto.kanji

import grails.test.GrailsUnitTestCase
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class ReviewSessionWithFramesTest extends GrailsUnitTestCase {

  private ReviewService reviewService = new ReviewService()
  private Review review = new Review()
  private def frame1 = new Frame(meaning: 'Schatz')
  private def frame2 = new Frame(meaning: 'Nichts')

  protected void setUp() {
    super.setUp()
    mockDomain Frame, [frame1, frame2]
    reviewService.random = mock(Random)
    when(reviewService.random.nextInt(2)).thenReturn(1)
    when(reviewService.random.nextInt(1)).thenReturn(0)
    reviewService.start(review)
  }

  void testHasCurrentFrameAccordingToRandomIdAfterStart() {
    assertEquals frame2, reviewService.getCurrentFrame(review)
  }

  void testHasRemainingFrameAfterResolve() {
    reviewService.resolve(review, true)
    assertEquals frame1, reviewService.getCurrentFrame(review)
  }

  void testHas2TotalFrames() {
    assertEquals 2, review.getTotalFrameCount()
  }

  void testResolvingFrameLeavesTotalFrameCountUnchanged() {
    reviewService.resolve(review, true)
    assertEquals 2, review.getTotalFrameCount()
  }

  void testStartsWithTotalFrameCountForRemainingCount() {
    assertEquals 2, review.getRemainingFrameCount()
  }

  void testReducesRemainingFrameCountOnResolve() {
    reviewService.resolve(review, true)
    assertEquals 1, review.getRemainingFrameCount()
  }

  void testStartsWithNoCorrectReviews() {
    assertEquals 0, review.correctReviewCount
  }

  void testStartsWithNoIncorrectReview() {
    assertEquals 0, review.incorrectReviewCount
  }

  void testRetainsIncorrectReviewsOnPositiveResolve() {
    reviewService.resolve(review, true)
    assertEquals 0, review.incorrectReviewCount
  }

  void testIncreasesCorrectReviewsOnPositiveResolve() {
    reviewService.resolve(review, true)
    assertEquals 1, review.correctReviewCount
  }

  void testRetainsCorrectReviewsOnNegativeResolve() {
    reviewService.resolve(review, false)
    assertEquals 0, review.correctReviewCount
  }

  void testIncreasesIncorrectReviewsOnNegativeResolve() {
    reviewService.resolve(review, false)
    assertEquals 1, review.incorrectReviewCount
  }
}