package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class LessonServiceWithFramesTest extends GrailsJUnit4TestCase {

  private LessonService service = new LessonService()

  @Before
  void mockDomainClasses() {
    mockDomain Frame.class, [new Frame(id: 1, lesson: 1), new Frame(id: 2, lesson: 1), new Frame(id: 3, lesson: 3)]
  }

  @Test
  void findsNumberOfLessonsAccordingToNumberOfDifferentLessonNumbers() {
    assertEquals 2, service.findAll().size()
  }

  @Test
  void sortsLessonsByNumber() {
    assertEquals([1, 3], service.findAll().collect { it.number })
  }

  @Test
  void findsLessonWithFramesForNumberOne() {
    assertEquals([1, 2], service.findAll()[0].frameIds)
  }

  @Test
  void findsLessonWithFrameForNumberTwo() {
    assertEquals([3], service.findAll()[1].frameIds)
  }
}