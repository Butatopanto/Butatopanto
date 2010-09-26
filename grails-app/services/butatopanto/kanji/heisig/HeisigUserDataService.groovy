package butatopanto.kanji.heisig

import butatopanto.kanji.Frame
import butatopanto.kanji.FrameReview
import butatopanto.kanji.UserData

class HeisigUserDataService {

  def userService

  def addFrameReviewsForCurrentUserAndLesson(def lesson) {
    def userData = findOrCreateUserData()
    Frame.findAllByLesson(lesson).each {
      userData.addToFrameReviews(new FrameReview(frame: it))
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