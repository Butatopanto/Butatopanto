package butatopanto.kanji

class HeisigUserDataService {

  def userService

  def addFrameReviewsForLesson(def lessonNumber) {
    def userData = findOrCreateUserData()
    Frame.findAllByLesson(lessonNumber).each {
      if (!FrameReview.findByFrame(it)) {
        userData.addToFrameReviews new FrameReview(frame: it)
      }
    }
  }

  def removeFrameReviewsForLesson(def lessonNumber) {
    def userData = findOrCreateUserData()
    Frame.findAllByLesson(lessonNumber).each {
      def review = FrameReview.findByFrameAndUserData(it, userData)
      if (review) {
        userData.removeFromFrameReviews review
      }
    }
  }

  def getActiveFrameIdsForLesson(def lessonNumber) {
    if (!currentUserData?.frameReviews) {
      return []
    }
    def allReviews = currentUserData.frameReviews as List
    def relevantReviews = allReviews.findAll {it.frame.lesson == lessonNumber}
    relevantReviews.collect {it.frame.id}
  }

  def getAllActiveFrameIds() {
    if (!currentUserData?.frameReviews) {
      return []
    }
    def allReviews = currentUserData.frameReviews as List
    allReviews.collect {it.frame.id}
  }

  private def findOrCreateUserData() {
    if (!currentUserData) {
      new UserData(userName: currentUserName).save()
    }
    currentUserData
  }

  def getCurrentUserData() {
    return UserData.findByUserName(currentUserName)
  }

  private String getCurrentUserName() {
    userService.currentUser.username
  }
}