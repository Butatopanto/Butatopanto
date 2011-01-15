package butatopanto.kanji;


import butatopanto.learning.Review
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class ReviewControllerTest extends GrailsJUnit4ControllerTestCase {

  ReviewControllerTest() {
    super(ReviewController)
  }

  private TestReviewService reviewService = new TestReviewService()
  private MasteryServiceObjectMother masteryServiceObjectMother = new MasteryServiceObjectMother()

  @Before
  void configureReviewService() {
    controller.reviewService = reviewService
  }

  @Before
  void configureMasteryService() {
    controller.masteryService = masteryServiceObjectMother.service
  }

  @Before
  void configureChapters() {
    controller.session.chapters = [
            new ChapterSelection(chapterNumber: 1),
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
    assertEquals "second", passedFrame.keyword
  }

  @Test
  void redirectsToManageAfterAddingChapter() {
    masteryServiceObjectMother.setNoDueFramesIdsForChapter()
    controller.params.id = "1"
    controller.addChapter()
    assertEquals "assemble", controller.redirectArgs.action
  }

  @Test
  void activatesChapterOnMasteryServiceOnAddChapter() {
    masteryServiceObjectMother.setNoDueFramesIdsForChapter()
    controller.params.id = "4"
    controller.addChapter()
    assertEquals([4], masteryServiceObjectMother.activatedChapters)
  }

  @Test
  void redirectsToManageAfterRemovingChapter() {
    controller.params.id = "3"
    controller.removeChapter()
    assertEquals "assemble", controller.redirectArgs.action
  }

  @Test
  void showsEndOfLessonScreenAfterResolvingLastFrame() {
    mockDomain Frame.class, [new Frame(number: 2, chapter: 1)]
    masteryServiceObjectMother.setNoDueFramesIdsForChapter()
    controller.reviewService = [resolve: {review, correct ->}, getCurrentFrame: {}, toNext: {review ->}]
    controller.session.review = new Review(currentReview: 2)
    controller.params.reviewCorrect = true
    controller.ajaxResolve()
    assertEquals "<h1>Herzlichen Glückwunsch</h1>", controller.response.contentAsString
  }

  @Test
  void clearsReviewAfterResolvingLastFrame() {
    mockDomain Frame.class, [new Frame(number: 2, chapter: 1)]
    masteryServiceObjectMother.setNoDueFramesIdsForChapter()
    controller.reviewService = [resolve: {review, correct ->}, getCurrentFrame: {}, toNext: {review ->}]
    controller.session.review = new Review(currentReview: 2)
    controller.params.reviewCorrect = true
    controller.ajaxResolve()
    assertNull controller.session.review
  }

  @Test
  void informsAssembleViewWhetherNoKanjiAreDue() {
    def result = controller.assemble()
    assertFalse result["dueFrames"]
  }

  @Test
  void informsAssembleViewsWhetherAnyKanjiAreDue() {
    setFramesDue()
    def result = controller.assemble()
    assertTrue result["dueFrames"]
  }

  private def setFramesDue() {
    controller.session.chapters[0].dueFrameCount = 1
  }
}