package butatopanto.kanji;


import butatopanto.test.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class LessonServiceWithFramesTest extends GrailsJUnit4TestCase {

  private LessonService service = new LessonService()

  @Before
  void mockDomainClasses() {
    mockDomain Frame.class, [new Frame(id: 1, lesson: 1), new Frame(id: 2, lesson: 1), new Frame(id: 3, lesson: 3)]
  }

  @Test
  void hasNumberOfLessonsAccordingToNumberOfDifferentLessonNumbers() {
    assertEquals 2, service.findAllLessons().size()
  }

  @Test
  void sortsLessonsByNumber() {
    assertEquals([1, 3], service.findAllLessons().collect { it.lessonNumber })
  }

  @Test
  void hasLessonWithFramesForNumberOne() {
    assertEquals([1, 2], service.findAllLessons()[0].frameIds)
  }

  @Test
  void hasLessonWithFrameForNumberTwo() {
    assertEquals([3], service.findAllLessons()[1].frameIds)
  }
}