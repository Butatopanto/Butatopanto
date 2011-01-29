package butatopanto.kanji

import butatopanto.learning.Review
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class ReviewControllerTest extends GrailsJUnit4ControllerTestCase {

  ReviewControllerTest() {
    super(ReviewController)
  }

  private TestReviewService reviewService = new TestReviewService()

  @Before
  void configureReviewService() {
    controller.reviewService = reviewService
  }

  @Test
  void passesCurrentFrameAsParameterForPractice() {
    controller.session.review = new Review(currentReview: "second")
    Frame passedFrame = controller.practice()."frame"
    assertEquals "second", passedFrame.keyword
  }

  @Test
  void showsEndOfLessonScreenAfterResolvingLastFrame() {
    mockDomain Frame.class, [new Frame(number: 2, chapter: 1)]
    controller.reviewService = [resolveAndAdvance: {review, correct ->}]
    controller.session.review = new Review(currentReview: 2)
    controller.params.reviewCorrect = true
    controller.ajaxResolve()
    assertEquals "<h1>Herzlichen Glückwunsch</h1>", controller.response.contentAsString
  }

  @Test
  void clearsReviewAfterResolvingLastFrame() {
    mockDomain Frame.class, [new Frame(number: 2, chapter: 1)]
    controller.reviewService = [resolveAndAdvance: {review, correct ->}]
    controller.session.review = new Review(currentReview: 2)
    controller.params.reviewCorrect = true
    controller.ajaxResolve()
    assertNull controller.session.review
  }

  private def setFramesDue() {
    controller.session.chapters[0].dueFrameCount = 1
    masteryServiceObjectMother.setDueFrames()
  }
}