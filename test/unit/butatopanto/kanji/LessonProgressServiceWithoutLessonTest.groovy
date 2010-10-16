package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class LessonProgressServiceWithoutLessonTest extends GrailsJUnit4TestCase {

  def LessonProgressService service = new LessonProgressService()

  @Before
  void mockLessonService() {
    service.lessonService = new TestLessonService(all: [])
  }

  @Test
  void findsNoLessonProgress() {
    assertEquals([], service.findAll())
  }
}