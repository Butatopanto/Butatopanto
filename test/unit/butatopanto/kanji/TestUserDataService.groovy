package butatopanto.kanji

class TestUserDataService {
  def currentUserData
  def activeFramesIdsByLesson = [:]
  def rightAnswers = []
  def wrongAnswers = []

  def getActiveFrameIdsForChapter(def lessonNumber) {
    if (activeFramesIdsByLesson[lessonNumber]) {
      return activeFramesIdsByLesson[lessonNumber]
    }
    []
  }

  def getActiveFrameIdsForChapterList(List chapterNumbers) {
    List activeFrames = []
    chapterNumbers.each {
      if (activeFramesIdsByLesson[it]) {
        activeFrames.addAll(activeFramesIdsByLesson[it])
      }
    }
    return activeFrames
  }

  def answerRight(def frameId) {
    rightAnswers.add(frameId)
  }

  def answerWrong(def frameId) {
    wrongAnswers.add(frameId)
  }
}
