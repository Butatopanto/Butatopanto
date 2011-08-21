package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class AssembleReviewController_DueKanjiTest extends GrailsJUnit4ControllerTestCase {

  AssembleReviewController_DueKanjiTest() {
    super(AssembleReviewController)
  }

  private TestChapterProgressService chapterProgressService = new TestChapterProgressService()

  @Before
  void configureChapterProgressService() {
    controller.chapterProgressService = chapterProgressService
  }

  @Test
  void informsAssembleViewAboutNoDueKanjiOnAssemble() {
    chapterProgressService.setNoDueFrameIds()
    def result = controller.assemble()
    assertFalse("Expected false, but was ${result['dueFrames']}", result["dueFrames"])
  }

  @Test
  void informsAssembleViewsAboutDueKanjiOnAssemble() {
    chapterProgressService.setAnyDueFrameIds()
    def result = controller.assemble()
    assertTrue("Expected true, but was ${result['dueFrames']}", result['dueFrames'])
  }
}