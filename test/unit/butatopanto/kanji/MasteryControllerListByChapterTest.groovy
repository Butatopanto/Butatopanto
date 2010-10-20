package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class MasteryControllerListByChapterTest extends GrailsJUnit4ControllerTestCase {

  MasteryControllerListByChapterTest() {
    super(MasteryController)
  }

  private activeFrameIdsByChapter = [:]

  @Before
  void mockFrames() {
    mockDomain Frame, [new Frame(id: 1, chapter: 1), new Frame(id: 2, chapter: 1), new Frame(id: 3, chapter: 2)]
  }

  @Before
  void mockMasteryService() {
    controller.masteryService = [listActiveFrameIdsForChapter: { chapterNumber ->
      activeFrameIdsByChapter[(Object) chapterNumber]
    }]
  }

  @Before
  void mockChapterService() {
    controller.chapterService = [
      getLastChapterNumber: {
        5
      },
      getFramesFor: {chapterNumber ->
        Frame.findByChapter chapterNumber
      }]
  }

  @Test
  void hasNoNextChapterForLastChapter() {
    controller.params.id = 5
    assertNull controller.listByChapter().next
  }

  @Test
  void hasPreviousChapterNumberAsPrevious() {
    controller.params.id = 5
    assertEquals 4, controller.listByChapter().previous
  }
}