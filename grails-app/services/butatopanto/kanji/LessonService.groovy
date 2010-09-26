package butatopanto.kanji

import butatopanto.kanji.bean.Lesson

class LessonService {

  static transactional = true

  def findAllLessons() {
    List<Frame> frameList = Frame.list()
    Set<Integer> lessonNumbers = ((frameList.collect {it.lesson} as Set) as List).sort()
    lessonNumbers.collect {
      def frameIds = Frame.findAllByLesson(it).collect {it.id}.sort()
      new Lesson(lessonNumber: it, frameIds: frameIds)
    }
  }
}