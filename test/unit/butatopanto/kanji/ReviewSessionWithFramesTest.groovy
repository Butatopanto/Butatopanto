package butatopanto.kanji

import grails.test.GrailsUnitTestCase
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class ReviewSessionWithFramesTest extends GrailsUnitTestCase {

  private ReviewSession reviewSession
  private def frame1 = new Frame(meaning: 'Schatz')
  private def frame2 = new Frame(meaning: 'Nichts')

  protected void setUp() {
    super.setUp()
    mockDomain Frame, [frame1, frame2]
    reviewSession = new ReviewSession()
    reviewSession.random = mock(Random)
    when(reviewSession.random.nextInt(2)).thenReturn(1)
    when(reviewSession.random.nextInt(1)).thenReturn(0)
    reviewSession.start()
  }

  void testHasCurrentFrameAccordingToRandomIdAfterStart() {
    assertEquals frame2, reviewSession.getCurrentFrame()
  }

  void testHasRemainingFrameAfterResolve() {
    reviewSession.resolve(true)
    assertEquals frame1, reviewSession.getCurrentFrame()
  }

  void testHas2TotalFrames() {
    assertEquals 2, reviewSession.getTotalFrameCount()
  }

  void testResolvingFrameLeavesTotalFrameCountUnchanged() {
    reviewSession.resolve(true)
    assertEquals 2, reviewSession.getTotalFrameCount()
  }

  void testStartsWithTotalFrameCountForRemainingCount() {
    assertEquals 2, reviewSession.getRemainingFrameCount()
  }

  void testReducesRemainingFrameCountOnResolve() {
    reviewSession.resolve(true)
    assertEquals 1, reviewSession.getRemainingFrameCount()
  }

  void testStartsWithNoCorrectReviews() {
    assertEquals 0, reviewSession.correctReviewCount
  }

  void testStartsWithNoIncorrectReview() {
    assertEquals 0, reviewSession.incorrectReviewCount
  }

  void testRetainsIncorrectReviewsOnPositiveResolve() {
    reviewSession.resolve(true)
    assertEquals 0, reviewSession.incorrectReviewCount
  }

  void testIncreasesCorrectReviewsOnPositiveResolve() {
    reviewSession.resolve(true)
    assertEquals 1, reviewSession.correctReviewCount
  }

  void testRetainsCorrectReviewsOnNegativeResolve() {
    reviewSession.resolve(false)
    assertEquals 0, reviewSession.correctReviewCount
  }

  void testIncreasesIncorrectReviewsOnNegativeResolve() {
    reviewSession.resolve(false)
    assertEquals 1, reviewSession.incorrectReviewCount
  }
}