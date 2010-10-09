package butatopanto.kanji

class HeisigUserDataService {

  def userService

  def activateReviewsForLesson(def lessonNumber) {
    def userData = findOrCreateUserData()
    Frame.findAllByLesson(lessonNumber).each {
      if (!FrameReview.findByFrame(it)) {
        userData.addToFrameReviews new FrameReview(frame: it)
      }
    }
  }

  def deactivateReviewsForLesson(def lessonNumber) {
    def userData = findOrCreateUserData()
    Frame.findAllByLesson(lessonNumber).each {
      def review = FrameReview.findByFrame(it)
      if (review) {
        userData.removeFromFrameReviews review
        review.delete()
      }
    }
  }

  def listActiveFrameIdsForChapter(int chapterNumber) {
    listActiveFrameIdsForChapterList([chapterNumber])
  }

  def listActiveFrameIdsForChapterList(List chapterNumbers) {
    def allReviews = listAllActiveReviews()
    def relevantReviews = allReviews.findAll {chapterNumbers.contains(it.frame.lesson)}
    relevantReviews.collect {it.frame.id}
  }

  def listAllActiveFrameIds() {
    def allReviews = listAllActiveReviews()
    allReviews.collect {it.frame.id}
  }

  List listActiveReviews(String sortAttribute, String order, int offset, int max) {
    List allFrameReviews = listAllActiveReviews()
    ReviewRequest.From(allFrameReviews)."sorted${order}By"(sortAttribute).startingFromIndex(offset).getAtMostElements(max)
  }

  def listAllActiveReviews() {
    if (!currentUserData?.frameReviews) {
      return []
    }
    currentUserData.frameReviews as List
  }

  def answerRight(def frameId) {
    def review = findActiveReviewByFrameId(frameId)
    review.passed += 1
    review.box += 1
    review.save()
  }

  def answerWrong(def frameId) {
    def review = findActiveReviewByFrameId(frameId)
    review.failed += 1
    review.box = FrameReview.FIRST_BOX
    review.save()
  }

  def findActiveReviewByFrameId(def frameId) {
    def currentUserData = getCurrentUserData();
    def allReviews = currentUserData.frameReviews as List
    allReviews.find {it.frame.id == frameId}
  }

  UserData getCurrentUserData() {
    return UserData.findByUserName(currentUserName)
  }

  int getReviewCount() {
    if (!currentUserData?.frameReviews) {
      return 0
    }
    return findOrCreateUserData().frameReviews.size()
  }

  private def findOrCreateUserData() {
    if (!currentUserData) {
      new UserData(userName: currentUserName).save()
    }
    currentUserData
  }

  private String getCurrentUserName() {
    userService.currentUser.username
  }
}