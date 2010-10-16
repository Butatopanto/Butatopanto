package butatopanto.kanji;


import org.junit.*
import butatopanto.sharedtest.GrailsJUnit4TestCase

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