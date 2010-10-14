package butatopanto.kanji

class TestMasteryService {
  def currentUserData
  def activeFramesIdsByLesson = [:]
  def rightAnswers = []
  def wrongAnswers = []

  def listActiveFrameIdsForChapter(def lessonNumber) {
    if (activeFramesIdsByLesson[lessonNumber]) {
      return activeFramesIdsByLesson[lessonNumber]
    }
    []
  }

  def listActiveFrameIdsForChapterList(List chapterNumbers) {
    List activeFrames = []
    chapterNumbers.each {
      if (activeFramesIdsByLesson[it]) {
        activeFrames.addAll(activeFramesIdsByLesson[it])
      }
    }
    return activeFrames
  }

  def listDueFrameIdsForChapter(int chapter) {
    []
  }

  def answerRight(def frameId) {
    rightAnswers.add(frameId)
  }

  def answerWrong(def frameId) {
    wrongAnswers.add(frameId)
  }
}
