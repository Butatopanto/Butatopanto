package butatopanto.kanji

class LessonProgressService {

  static transactional = true
  def masteryService
  def lessonService

  def findAll() {
    def lessons = lessonService.findAll()
    lessons.collect {
      def activeFrameIds = masteryService.listActiveFrameIdsForChapter(it.number)
      def dueFrameIds = masteryService.listDueFrameIdsForChapter(it.number)
      new LessonProgress(lesson: it, activeFrameIds: activeFrameIds, dueFrameIds: dueFrameIds)
    }
  }
}