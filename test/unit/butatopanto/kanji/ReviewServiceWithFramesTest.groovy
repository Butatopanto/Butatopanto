package butatopanto.kanji

import butatopanto.kanji.bean.Review
import grails.test.GrailsUnitTestCase
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class ReviewServiceWithFramesTest extends GrailsUnitTestCase {

  private ReviewService reviewService = new ReviewService()
  private Review review = new Review()
  private def frame1 = new Frame(id: 1, meaning: 'Schatz')
  private def frame2 = new Frame(id: 2, meaning: 'Nichts')

  protected void setUp() {
    super.setUp()
    mockDomain Frame, [frame1, frame2, new Frame(id: 3, meaning: 'inactive')]
    reviewService.random = mock(Random)
    when(reviewService.random.nextInt(2)).thenReturn(1)
    when(reviewService.random.nextInt(1)).thenReturn(0)
    reviewService.heisigUserDataService = new TestUserDataService(activeFramesIdsByLesson: [1: [1, 2]])
    reviewService.start(review, [1])
  }

  void testHasCurrentFrameAccordingToRandomIdAfterStart() {
    assertEquals frame2, reviewService.getCurrentFrame(review)
  }

  void testHasRemainingFrameAfterResolve() {
    reviewService.resolve(review, true)
    assertEquals frame1, reviewService.getCurrentFrame(review)
  }

  void testNumberOfActiveFramesAsTotalCount() {
    assertEquals 2, review.totalCount
  }

  void testResolvingFrameLeavesTotalFrameCountUnchanged() {
    reviewService.resolve(review, true)
    assertEquals 2, review.totalCount
  }

  void testStartsWithTotalFrameCountForRemainingCount() {
    assertEquals 2, review.getRemainingCount()
  }

  void testReducesRemainingFrameCountOnResolve() {
    reviewService.resolve(review, true)
    assertEquals 1, review.getRemainingCount()
  }

  void testStartsWithNoCorrectReviews() {
    assertEquals 0, review.rightCount
  }

  void testStartsWithNoIncorrectReview() {
    assertEquals 0, review.wrongCount
  }

  void testRetainsIncorrectReviewsOnPositiveResolve() {
    reviewService.resolve(review, true)
    assertEquals 0, review.wrongCount
  }

  void testIncreasesCorrectReviewsOnPositiveResolve() {
    reviewService.resolve(review, true)
    assertEquals 1, review.rightCount
  }

  void testRetainsCorrectReviewsOnNegativeResolve() {
    reviewService.resolve(review, false)
    assertEquals 0, review.rightCount
  }

  void testIncreasesIncorrectReviewsOnNegativeResolve() {
    reviewService.resolve(review, false)
    assertEquals 1, review.wrongCount
  }
}