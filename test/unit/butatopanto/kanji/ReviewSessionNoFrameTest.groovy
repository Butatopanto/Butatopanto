package butatopanto.kanji

import grails.test.GrailsUnitTestCase
import static org.mockito.Mockito.mock

class ReviewSessionNoFrameTest extends GrailsUnitTestCase {

  private ReviewSession reviewSession
  private Random random = mock(Random)

  protected void setUp() {
    super.setUp()
    mockDomain Frame
    reviewSession = new ReviewSession()
    reviewSession.random = random
    reviewSession.start()
  }

  void testHasNoCurrentFrame() {
    assertNull reviewSession.getCurrentFrame()
  }

  void testHasNoCurrentFrameAfterResolve() {
    reviewSession.resolve(true)
    assertNull reviewSession.getCurrentFrame()
  }

  void testHasNoTotalFrame() {
    assertEquals 0, reviewSession.getTotalFrameCount()
  }

  void testStartsWithTotalFrameCountForRemainingCount() {
    assertEquals 0, reviewSession.getRemainingFrameCount()
  }
}
