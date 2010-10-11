package butatopanto.kanji;


import butatopanto.kanji.bean.Lesson

import butatopanto.test.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class LessonProgressServiceWithLessonTest extends GrailsJUnit4TestCase {

  private LessonProgressService service = new LessonProgressService()
  private UserData userData = new UserData()
  private def activeFramesByLesson = [:];
  private def allFrames = [new Frame(id: 1), new Frame(id: 2), new Frame(id: 3)]
  
  @Before
  void mockHeisigUserDataService() {
    service.heisigUserDataService = new TestUserDataService(activeFramesIdsByLesson: activeFramesByLesson)
  }

  @Before
  void mockLessonService() {
    def allLessons = [
      new Lesson(number: 1, frameIds: [1, 2]),
      new Lesson(number: 2, frameIds: [3])
    ]
    service.lessonService = new TestLessonService(all: allLessons)
  }

  @Before
  void mockDomain() {
    mockDomain MasteryOfFrame
    mockDomain Frame, allFrames
  }

  @Test
  void findsALessonProgressForEachLesson() {
    def progressList = service.findAll()
    assertEquals([1, 2], progressList.collect {it.lesson.number})
  }

  @Test
  void findsLessonProgressWithoutActiveFrame() {
    def progressList = service.findAll()
    assertEquals([[], []], progressList.collect {it.activeFrameIds})
  }

  @Test
  void findsLessonProgressWithActiveFramesForExistingFrameReviews() {
    activeFramesByLesson[1] = [1]
    assertEquals([[1], []], service.findAll().collect {it.activeFrameIds})
  }
}