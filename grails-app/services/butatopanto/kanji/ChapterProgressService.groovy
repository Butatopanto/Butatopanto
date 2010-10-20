package butatopanto.kanji

class ChapterProgressService {

  static transactional = true
  def masteryService
  def chapterService

  def findAll() {
    def lessons = chapterService.findAll()
    lessons.collect {
      def activeFrameIds = masteryService.listActiveFrameIdsForChapter(it.number)
      def dueFrameIds = masteryService.listDueFrameIdsForChapter(it.number)
      new ChapterProgress(chapter: it, activeFrameIds: activeFrameIds, dueFrameIds: dueFrameIds)
    }
  }
}