package butatopanto.kanji

class TestUserDataService {
  def currentUserData
  def activeFramesByLesson
  def allActiveFrameIds

  def getActiveFrameIdsForLesson(def lessonNumber) {
    if (activeFramesByLesson[lessonNumber]) {
      return activeFramesByLesson[lessonNumber]
    }
    []
  }
}
