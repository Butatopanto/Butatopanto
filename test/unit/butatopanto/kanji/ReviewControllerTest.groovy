package butatopanto.kanji;


import butatopanto.kanji.bean.Review
import grails.test.ControllerUnitTestCase
import butatopanto.kanji.bean.ChapterSelection

class ReviewControllerTest extends ControllerUnitTestCase {

  ReviewControllerTest() {
    super(ReviewController)
  }

  private TestReviewService reviewService = new TestReviewService()

  protected void setUp() {
    super.setUp()
    controller.reviewService = reviewService
    controller.session.chapters = [new ChapterSelection(chapterNumber: 1),
      new ChapterSelection(chapterNumber: 2),
      new ChapterSelection(chapterNumber: 3),
      new ChapterSelection(chapterNumber: 4)]
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

  def testRedirectsToManageAfterAddingLesson() {
    controller.heisigUserDataService = [addFrameReviewsForLesson: { }]
    controller.params.id = "1"
    controller.addLesson()
    assertEquals "manage", controller.redirectArgs.action
  }

  def testAddsLessonReviewsViaService() {
    int addedLesson
    controller.heisigUserDataService = [addFrameReviewsForLesson: {addedLesson = it}]
    controller.params.id = "4"
    controller.addLesson()
    assertEquals 4, addedLesson
  }

  def testRedirectsToManageAfterRemovingLesson() {
    controller.heisigUserDataService = [removeFrameReviewsForLesson: { }]
    controller.params.id = "3"
    controller.removeLesson()
    assertEquals "manage", controller.redirectArgs.action
  }

  def testDoesNotRemoveLessonReviewsViaService() {
    int removedLesson
    controller.heisigUserDataService = [removeFrameReviewsForLesson: {removedLesson = it}]
    controller.params.id = "3"
    controller.removeLesson()
    assertNull removedLesson
  }
}