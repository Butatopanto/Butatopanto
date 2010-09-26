package butatopanto.kanji.heisig

import butatopanto.kanji.UserData
import butatopanto.kanji.Frame
import butatopanto.kanji.FrameReview

class HeisigUserDataService {

  def userService

  def addFrameReviewsForCurrentUserAndLesson(def lesson) {
    def userData = findOrCreateUserData()
    Frame.findAllByLesson(lesson).each {
      userData.addToFrameReviews(new FrameReview(frame: it))
    }
  }

  private def findOrCreateUserData() {
    def userName = userService.currentUser.username
    if (!UserData.findByUserName(userName)) {
      def userData = new UserData(userName: userName)
      userData.save()
    }
    UserData.findByUserName(userName)
  }
}