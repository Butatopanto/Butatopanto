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
}