package butatopanto.kanji

import butatopanto.sharedtest.GrailsJUnit4TestCase
import butatopanto.sharedtest.ValidationUtilities
import org.junit.Before
import org.junit.Test
import butatopanto.learning.Review
import butatopanto.security.User

class ReviewServiceIntegrationTest extends GrailsJUnit4TestCase {

  ReviewService reviewService

  @Test
  void hasNoCurrentFrameForEmptyReview() {
    assertNull reviewService.getCurrentFrame(new Review())
  }
}