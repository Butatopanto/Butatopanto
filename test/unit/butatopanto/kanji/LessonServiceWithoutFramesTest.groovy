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
  void hasNoLessonsWithoutFrames() {
    assertEquals 0, service.findAllLessons().size()
  }

  @Test
  void hasOneLessonsOfSizeOneWithSingleFrame() {
    new Frame(lesson: 1).save()
    assertEquals 0, service.findAllLessons().size()
  }
}