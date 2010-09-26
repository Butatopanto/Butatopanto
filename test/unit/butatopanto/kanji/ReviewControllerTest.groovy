package butatopanto.kanji;

import grails.test.ControllerUnitTestCase
import butatopanto.kanji.bean.Review

class ReviewControllerTest extends ControllerUnitTestCase {

  ReviewControllerTest() {
    super(ReviewController)
  }

  private TestReviewService reviewService = new TestReviewService()

  protected void setUp() {
    super.setUp()
    controller.reviewService = reviewService
  }

  def testStoresNewStartedReviewInSessionOnStart() {
    controller.start()
    assertNotNull controller.session.review
    assertSame reviewService.lastStartedReview, controller.session.review
  }

  def testStoresStartedInSessionOnStart() {
    controller.start()
    assertNotNull controller.session.review
    assertSame reviewService.lastStartedReview, controller.session.review
  }

  def testRedirectsToPracticeOnStart() {
    controller.start()
    assertEquals "practice", controller.redirectArgs.action
  }

  def testRedirectsToStartOnPracticeWithoutActiveReview() {
    controller.practice()
    assertEquals "start", controller.redirectArgs.action
  }

  def testDoesNotStartReviewOnPractice() {
    def originalReview = new Review(currentReview: "second")
    controller.session.review = originalReview
    controller.practice()
    assertSame originalReview, controller.session.review
    assertNull reviewService.lastStartedReview
  }

  def testPassesCurrentFrameAsParameterForPractice() {
    controller.session.review = new Review(currentReview: "second")
    Frame passedFrame = controller.practice()."frame"
    assertEquals "second", passedFrame.meaning
  }
}