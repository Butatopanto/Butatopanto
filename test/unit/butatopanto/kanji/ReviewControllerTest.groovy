package butatopanto.kanji;


import butatopanto.kanji.bean.ChapterSelection
import butatopanto.kanji.bean.Review
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

  @Before
  void configureChapters() {
    controller.session.chapters = [new ChapterSelection(chapterNumber: 1),
            new ChapterSelection(chapterNumber: 2),
            new ChapterSelection(chapterNumber: 3),
            new ChapterSelection(chapterNumber: 4)]
  }

  @Test
  void storesNewStartedReviewInSessionOnStart() {
    controller.startSelectedChapters()
    assertNotNull controller.session.review
    assertSame reviewService.lastStartedReview, controller.session.review
  }

  @Test
  void storesStartedInSessionOnStart() {
    controller.startSelectedChapters()
    assertNotNull controller.session.review
    assertSame reviewService.lastStartedReview, controller.session.review
  }

  @Test
  void redirectsToPracticeOnStart() {
    controller.startSelectedChapters()
    assertEquals "practice", controller.redirectArgs.action
  }

  @Test
  void redirectsToStartDueOnPracticeWithoutActiveReview() {
    controller.practice()
    assertEquals "startDue", controller.redirectArgs.action
  }

  @Test
  void doesNotStartReviewOnPractice() {
    def originalReview = new Review(currentReview: "second")
    controller.session.review = originalReview
    controller.practice()
    assertSame originalReview, controller.session.review
    assertNull reviewService.lastStartedReview
  }

  @Test
  void passesCurrentFrameAsParameterForPractice() {
    controller.session.review = new Review(currentReview: "second")
    Frame passedFrame = controller.practice()."frame"
    assertEquals "second", passedFrame.meaning
  }

  @Test
  void redirectsToManageAfterAddingLesson() {
    controller.masteryService = [activateLesson: { }, listDueFrameIdsForChapter: {[]}]
    controller.params.id = "1"
    controller.addLesson()
    assertEquals "manage", controller.redirectArgs.action
  }

  @Test
  void addsLessonReviewsViaService() {
    int addedLesson
    controller.masteryService = [activateLesson: {addedLesson = it}, listDueFrameIdsForChapter: {[]}]
    controller.params.id = "4"
    controller.addLesson()
    assertEquals 4, addedLesson
  }

  @Test
  void redirectsToManageAfterRemovingLesson() {
    controller.masteryService = [deactivateLesson: { }]
    controller.params.id = "3"
    controller.removeLesson()
    assertEquals "manage", controller.redirectArgs.action
  }

  @Test
  void doesNotRemoveLessonReviewsViaService() {
    int removedLesson
    controller.masteryService = [deactivateLesson: {removedLesson = it}]
    controller.params.id = "3"
    controller.removeLesson()
    assertNull removedLesson
  }

  @Test
  void showsEndOfLessonScreenAfterResolvingLastFrame() {
    controller.reviewService = [resolve: {review, correct ->}, getCurrentFrame: {}]
    controller.masteryService = [listDueFrameIdsForChapter: {[]}]
    controller.session.review = new Review(currentReview: "second")
    controller.params.reviewCorrect = true
    controller.ajaxResolve()
    assertEquals "<h1>Herzlichen Glückwunsch</h1>", controller.response.contentAsString
  }

  @Test
  void clearsReviewAfterResolvingLastFrame() {
    controller.reviewService = [resolve: {review, correct ->}, getCurrentFrame: {}]
    controller.masteryService = [listDueFrameIdsForChapter: {[]}]
    controller.session.review = new Review(currentReview: "second")
    controller.params.reviewCorrect = true
    controller.ajaxResolve()
    assertNull controller.session.review 
  }
}