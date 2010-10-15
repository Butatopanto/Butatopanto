package butatopanto.kanji;


import butatopanto.kanji.bean.ChapterSelection
import butatopanto.kanji.bean.Review
import butatopanto.test.GrailsJUnit4ControllerTestCase
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

  @Before
  void configureChapters() {
    controller.session.chapters = [new ChapterSelection(chapterNumber: 1),
      new ChapterSelection(chapterNumber: 2),
      new ChapterSelection(chapterNumber: 3),
      new ChapterSelection(chapterNumber: 4)]
  }

  @Test
  void testStoresNewStartedReviewInSessionOnStart() {
    controller.startSelectedChapters()
    assertNotNull controller.session.review
    assertSame reviewService.lastStartedReview, controller.session.review
  }

  @Test
  void testStoresStartedInSessionOnStart() {
    controller.startSelectedChapters()
    assertNotNull controller.session.review
    assertSame reviewService.lastStartedReview, controller.session.review
  }

  @Test
  void testRedirectsToPracticeOnStart() {
    controller.startSelectedChapters()
    assertEquals "practice", controller.redirectArgs.action
  }

  @Test
  void redirectsToStartDueOnPracticeWithoutActiveReview() {
    controller.practice()
    assertEquals "startDue", controller.redirectArgs.action
  }

  @Test
  void testDoesNotStartReviewOnPractice() {
    def originalReview = new Review(currentReview: "second")
    controller.session.review = originalReview
    controller.practice()
    assertSame originalReview, controller.session.review
    assertNull reviewService.lastStartedReview
  }

  @Test
  void testPassesCurrentFrameAsParameterForPractice() {
    controller.session.review = new Review(currentReview: "second")
    Frame passedFrame = controller.practice()."frame"
    assertEquals "second", passedFrame.meaning
  }

  @Test
  void testRedirectsToManageAfterAddingLesson() {
    controller.masteryService = [activateLesson: { }, listDueFrameIdsForChapter: {[]}]
    controller.params.id = "1"
    controller.addLesson()
    assertEquals "manage", controller.redirectArgs.action
  }

  @Test
  void testAddsLessonReviewsViaService() {
    int addedLesson
    controller.masteryService = [activateLesson: {addedLesson = it}, listDueFrameIdsForChapter: {[]}]
    controller.params.id = "4"
    controller.addLesson()
    assertEquals 4, addedLesson
  }

  @Test
  void testRedirectsToManageAfterRemovingLesson() {
    controller.masteryService = [deactivateLesson: { }]
    controller.params.id = "3"
    controller.removeLesson()
    assertEquals "manage", controller.redirectArgs.action
  }

  @Test
  void testDoesNotRemoveLessonReviewsViaService() {
    int removedLesson
    controller.masteryService = [deactivateLesson: {removedLesson = it}]
    controller.params.id = "3"
    controller.removeLesson()
    assertNull removedLesson
  }
}