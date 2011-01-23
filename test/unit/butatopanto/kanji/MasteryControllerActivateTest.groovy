package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class MasteryControllerActivateTest extends GrailsJUnit4ControllerTestCase {

  MasteryControllerActivateTest() {
    super(MasteryController)
  }

  private storyByFrameId = [:]
  private def inactiveFrameIds = []
  private def activated;

  @Before
  void mockFrames() {
    mockDomain Frame, [new Frame(id: 1, number: 1, chapter: 1), new Frame(id: 2, chapter: 1), new Frame(id: 3, number: 3, chapter: 2)]
  }

  @Before
  void mockStoryService() {
    controller.storyService = [
            findStoryTextByFrameId: { frameId ->
              storyByFrameId[frameId]
            }
    ]
  }

  @Before
  void mockMasteryService() {
    controller.masteryService = [activateRange: { from, to ->
      activated = new IntRange(from, to)
    }]
  }

  @Before
  void mockChapterService() {
    controller.chapterService = [
            getLastChapterNumber: {
              5
            },
            listFramesFor: {chapterNumber ->
              Frame.findAllByChapter chapterNumber
            }]
  }

  @Test
  void activatesGivenFromFrames() {
    controller.params.from = 1
    controller.params.to = 5
    controller.activate()
    assertEquals(1, activated.getFromInt())
  }

  @Test
  void activatesGivenToFrames() {
    controller.params.from = 1
    controller.params.to = 5
    controller.activate()
    assertEquals(5, activated.getToInt())
  }

  @Test
  void showsChapterWithFromKanjiInItAfterwards() {
    controller.params.from = 3
    controller.params.to = 5
    controller.activate()
    assertEquals([action: "listByChapter", id: 2], controller.redirectArgs)
  }
}