package butatopanto.kanji

import butatopanto.kanji.bean.LessonProgress

class LessonProgressService {

  static transactional = true
  def heisigUserDataService
  def lessonService

  def findAll() {
    def lessons = lessonService.findAll()
    lessons.collect {
      def activeFrameIds = heisigUserDataService.getActiveFrameIdsForChapter(it.number)
      new LessonProgress(lesson: it, activeFrameIds: activeFrameIds)
    }
  }
}