package butatopanto.kanji

class TestChapterProgressService {

  def dueFrameIds = 1..15

  def findAll() {
    return [new ChapterProgress(chapter: new Chapter(number: 1, frameIds: 1..15), activeFrameIds: 1..15, dueFrameIds: dueFrameIds)]
  }

  def setNoDueFrameIds() {
    dueFrameIds = []
  }

  def setAnyDueFrameIds() {
    dueFrameIds = 1..15
  }
}
