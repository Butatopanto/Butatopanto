package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test


class ChapterProgressServiceWithoutLessonTest extends GrailsJUnit4TestCase {

  def ChapterProgressService service = new ChapterProgressService()

  @Before
  void mockChapterService() {
    service.chapterService = new TestLessonService(all: [])
  }

  @Test
  void findsNoChapterProgress() {
    assertEquals([], service.findAll())
  }
}