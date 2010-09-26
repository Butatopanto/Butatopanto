package butatopanto.kanji;

import butatopanto.test.*
import org.junit.*

class LessonServiceWithoutFramesTest extends GrailsJUnit4TestCase {

  private LessonService service = new LessonService()

  @Before
  void mockDomainClasses() {
    mockDomain Frame.class
  }

  @Test
  void findsNoLessonsWithoutFrames() {
    assertEquals 0, service.findAll().size()
  }
}