package butatopanto.kanji;

import grails.test.ControllerUnitTestCase

class ReviewControllerFrameActionTest extends ControllerUnitTestCase {

  ReviewControllerFrameActionTest() {
    super(ReviewController)
  }

  private TestReviewService reviewService = new TestReviewService()

  protected void setUp() {
    super.setUp()
    controller.reviewService = reviewService
  }

  def testStoresReviewStartedByServiceInSession() {
    controller.frame()
    assertNotNull controller.session.review
    assertSame reviewService.lastStartedReview, controller.session.review
  }

  def testPassesCurrentFrameAsParameter() {
    assertPassesFrameWithMeaning reviewService.initialId
  }

  def testDoesNotRestartExistingReview() {
    def originalReview = new Review(currentId: "second")
    controller.session.review = originalReview
    controller.frame()
    assertSame originalReview, controller.session.review
    assertNull reviewService.lastStartedReview
  }

  def testPassesCurrentFrameAsParameterForExistingReview() {
    controller.session.review = new Review(currentId: "second")
    assertPassesFrameWithMeaning("second")
  }

  private def assertPassesFrameWithMeaning(meaning) {
    Frame passedFrame = controller.frame()."frame"
    assertEquals meaning, passedFrame.meaning
  }
}