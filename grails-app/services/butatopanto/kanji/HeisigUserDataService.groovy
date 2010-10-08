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
      def review = FrameReview.findByFrame(it)
      if (review) {
        userData.removeFromFrameReviews review
        review.delete()
      }
    }
  }

  def getActiveFrameIdsForChapter(int chapterNumber) {
    getActiveFrameIdsForChapterList([chapterNumber])
  }

  def getActiveFrameIdsForChapterList(List chapterNumbers) {
    if (!currentUserData?.frameReviews) {
      return []
    }
    def allReviews = currentUserData.frameReviews as List
    def relevantReviews = allReviews.findAll {chapterNumbers.contains(it.frame.lesson)}
    relevantReviews.collect {it.frame.id}
  }

  def getAllActiveFrameIds() {
    if (!currentUserData?.frameReviews) {
      return []
    }
    def allReviews = currentUserData.frameReviews as List
    allReviews.collect {it.frame.id}
  }

  def answerRight(def frameId) {
    def review = findFrameReviewByFrameId(frameId)
    review.passed += 1
    review.box += 1
    review.save()
  }

  def answerWrong(def frameId) {
    def review = findFrameReviewByFrameId(frameId)
    review.failed += 1
    review.box = FrameReview.FIRST_BOX
    review.save()
  }

  def findFrameReviewByFrameId(def frameId) {
    def currentUserData = getCurrentUserData();
    def allReviews = currentUserData.frameReviews as List
    allReviews.find {it.frame.id == frameId}
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