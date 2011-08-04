package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class AssembleReviewController_DueKanjiTest extends GrailsJUnit4ControllerTestCase {

    AssembleReviewController_DueKanjiTest() {
        super(AssembleReviewController)
    }

    private MasteryServiceObjectMother masteryServiceObjectMother = new MasteryServiceObjectMother()

    @Before
    void configureMasteryService() {
        masteryServiceObjectMother.setNoDueFramesIds()
        controller.masteryService = masteryServiceObjectMother.service
    }

    @Before
    void configureChapterService() {
        controller.chapterProgressService = new TestChapterProgressService()
    }

    @Before
    void configureChapters() {
        controller.session.chapters = [new ChapterSelection(chapterNumber: 1)]
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
        masteryServiceObjectMother.setDueFrames()
    }
}