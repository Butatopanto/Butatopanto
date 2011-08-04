package butatopanto.kanji;


import butatopanto.learning.Review
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class AssembleReviewControllerTest extends GrailsJUnit4ControllerTestCase {

    AssembleReviewControllerTest() {
        super(AssembleReviewController)
    }

    private TestReviewService reviewService = new TestReviewService()
    private MasteryServiceObjectMother masteryServiceObjectMother = new MasteryServiceObjectMother()

    @Before
    void configureReviewService() {
        controller.reviewService = reviewService
    }

    @Before
    void configureMasteryService() {
        masteryServiceObjectMother.setNoDueFramesIds()
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
    void redirectsToManageAfterAddingChapter() {
        masteryServiceObjectMother.setNoDueFramesIds()
        controller.params.id = "1"
        controller.addChapter()
        assertEquals "continueToAssemble", controller.redirectArgs.action
    }

    @Test
    void redirectsToManageAfterRemovingChapter() {
        controller.params.id = "3"
        controller.removeChapter()
        assertEquals "continueToAssemble", controller.redirectArgs.action
    }

    @Test
    void startsPracticeForList() {
        controller.flash.kanji = [75, 76]
        controller.startList()
        assertEquals "practice", controller.redirectArgs.action
    }

    @Test
    void createsReviewForList() {
        controller.flash.kanji = [75, 76]
        controller.startList()
        assertEquals([75, 76], controller.session.review.remainingIds)
    }

    @Test
    void continuesAssemblyIfNoKanjiGiven() {
        controller.startList()
        assertEquals "continueToAssemble", controller.redirectArgs.action
    }

    private def setFramesDue() {
        controller.session.chapters[0].dueFrameCount = 1
        masteryServiceObjectMother.setDueFrames()
    }
}