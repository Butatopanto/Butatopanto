package butatopanto.learning

import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test
import butatopanto.learning.Review

class ReviewTest extends GrailsJUnit4TestCase {

  private Review review = new Review()

  @Test
  public void isFinishedWithoutRemainingFrames() {
     assertTrue review.isFinished()
  }

  @Test
  public void isNotFinishedWithRemainingFrames() {
    review.remainingIds = [2]
    assertFalse review.isFinished()
  }
}
