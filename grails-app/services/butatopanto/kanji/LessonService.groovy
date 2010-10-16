package butatopanto.kanji

class LessonService {

  static transactional = true

  def findAll() {
    List<Frame> frameList = Frame.list()
    Set<Integer> lessonNumbers = ((frameList.collect {it.lesson} as Set) as List).sort()
    lessonNumbers.collect {
      new Lesson(number: it, frameIds: getFramesFor(it))
    }
  }

  def getFramesFor(def lessonId) {
     Frame.findAllByLesson(lessonId).collect {it.id}.sort()
  }
}