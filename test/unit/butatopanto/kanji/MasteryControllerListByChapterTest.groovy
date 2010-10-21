package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class MasteryControllerListByChapterTest extends GrailsJUnit4ControllerTestCase {

  MasteryControllerListByChapterTest() {
    super(MasteryController)
  }

  private storyByFrameId = [:]
  private def inactiveFrameIds = []

  @Before
  void mockFrames() {
    mockDomain Frame, [new Frame(id: 1, chapter: 1), new Frame(id: 2, chapter: 1), new Frame(id: 3, chapter: 2)]
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
    controller.masteryService = [findMasteryByFrameId: { frameId ->
      def frame = Frame.get(frameId)
      if (inactiveFrameIds.contains(frameId)) {
        return null
      }
      return new MasteryOfFrame(frame: frame, box: frameId + 1)
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
  void hasNoNextChapterForLastChapter() {
    assertNull listByChapter(5).next
  }

  @Test
  void hasPreviousChapterNumberAsPrevious() {
    assertEquals 4, listByChapter(5).previous
  }

  @Test
  void hasMasteryFramesForEachChapterFrame() {
    assertEquals([1, 2], listByChapter(1).masteredFrames.collect {it.frame.id})
  }

  @Test
  void hasMasteryFramesWithBoxesFromMasteryService() {
    assertEquals([2, 3], listByChapter(1).masteredFrames.collect {it.box})
  }

  @Test
  void hasMasteryFramesWithBoxZeroForInactiveFrame() {
    inactiveFrameIds.add 1L
    inactiveFrameIds.add 2L
    assertEquals([0, 0], listByChapter(1).masteredFrames.collect {it.box})
  }

  @Test
  void hasMasteryFramesWithoutStoriesAccordingToStoryService() {
    storyByFrameId[1L] = "A story"
    def result = listByChapter(1)
    assertEquals([true, false], result.masteredFrames.collect {it.hasStory})
  }

  private def listByChapter(int chapterNumber) {
    controller.params.id = chapterNumber
    def result = controller.listByChapter()
  }
}