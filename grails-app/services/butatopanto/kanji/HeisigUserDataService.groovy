package butatopanto.kanji

class HeisigUserDataService {

  def userService

  def addFrameReviewsForCurrentUserAndLesson(def lessonNumber) {
    def userData = findOrCreateUserData()
    Frame.findAllByLesson(lessonNumber).each {
      if (!FrameReview.findByFrame(it)) {
        userData.addToFrameReviews(new FrameReview(frame: it))
      }
    }
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