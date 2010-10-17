package butatopanto.kanji;

import butatopanto.sharedtest.*
import org.junit.*
import butatopanto.learning.Review

class CurrentStoryFromReviewServiceTest extends GrailsJUnit4TestCase {

  private ReviewService service = new ReviewService()
  private def storiesByFrameId = [:]

  @Before
  void mockStoryService() {
    service.storyService = [findStoryTextByFrameId: { frameId ->
      storiesByFrameId[frameId]
    }]
  }

  @Test
  void returnsStoryFromStoryServiceForFrameWithCurrentReviewId() {
    Review review = new Review(currentReview: 2)
    storiesByFrameId[2] = "Woot!"
    assertEquals "Woot!", service.getCurrentStory(review)
  }

  @Test
  void returnsEmptyStringWithoutCurrentReview() {
    Review review = new Review()
    assertEquals "", service.getCurrentStory(review)
  }
}