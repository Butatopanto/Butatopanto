package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class ChapterProgressServiceWithLessonTest extends GrailsJUnit4TestCase {

  private ChapterProgressService service = new ChapterProgressService()
  private HeisigUser userData = new HeisigUser()
  private def activeFramesByChapter = [:];
  private def allFrames = [new Frame(id: 1), new Frame(id: 2), new Frame(id: 3)]
  
  @Before
  void mockHeisigUserDataService() {
    service.masteryService = new TestMasteryService(activeFramesIdsByLesson: activeFramesByChapter)
  }

  @Before
  void mockChapterService() {
    def allChapters = [
      new Chapter(number: 1, frameIds: [1, 2]),
      new Chapter(number: 2, frameIds: [3])
    ]
    service.chapterService = new TestLessonService(all: allChapters)
  }

  @Before
  void mockDomain() {
    mockDomain MasteryOfFrame
    mockDomain Frame, allFrames
  }

  @Test
  void findsAChapterProgressForEachChapter() {
    def progressList = service.findAll()
    assertEquals([1, 2], progressList.collect {it.chapter.number})
  }

  @Test
  void findsChapterProgressWithoutActiveFrame() {
    def progressList = service.findAll()
    assertEquals([[], []], progressList.collect {it.activeFrameIds})
  }

  @Test
  void findsChapterProgressWithActiveFramesForExistingFrameReviews() {
    activeFramesByChapter[1] = [1]
    assertEquals([[1], []], service.findAll().collect {it.activeFrameIds})
  }
}