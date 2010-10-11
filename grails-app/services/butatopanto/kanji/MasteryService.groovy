package butatopanto.kanji

import butatopanto.request.ListRequester

class MasteryService {

  static transactional = true
  def userService

  def activateLesson(def lessonNumber) {
    def userData = findOrCreateUserData()
    Frame.findAllByLesson(lessonNumber).each {
      if (!MasteryOfFrame.findByFrame(it)) {
        userData.addToMasteryList new MasteryOfFrame(frame: it)
      }
    }
  }

  def deactivateLesson(def lessonNumber) {
    def userData = findOrCreateUserData()
    Frame.findAllByLesson(lessonNumber).each {
      def review = MasteryOfFrame.findByFrame(it)
      if (review) {
        userData.removeFromMasteryList review
        review.delete()
      }
    }
  }

  def listActiveFrameIdsForChapter(int chapterNumber) {
    listActiveFrameIdsForChapterList([chapterNumber])
  }

  def listActiveFrameIdsForChapterList(List chapterNumbers) {
    def allReviews = listMastery()
    def relevantReviews = allReviews.findAll {chapterNumbers.contains(it.frame.lesson)}
    relevantReviews.collect {it.frame.id}
  }

  def listActiveFrameIds() {
    def allReviews = listMastery()
    allReviews.collect {it.frame.id}
  }

  List listMastery(String sortAttribute, String order, int offset, int max) {
    List allActiveReviews = listMastery()
    ListRequester.From(allActiveReviews)."sorted${order}By"(sortAttribute).startingFromIndex(offset).getAtMostElements(max)
  }

  def listMastery() {
    if (!currentUserData?.masteryList) {
      return []
    }
    currentUserData.masteryList as List
  }

  def answerRight(def frameId) {
    def review = findMasteryByFrameId(frameId)
    review.passed += 1
    review.box += 1
    review.save()
  }

  def answerWrong(def frameId) {
    def review = findMasteryByFrameId(frameId)
    review.failed += 1
    review.box = MasteryOfFrame.FIRST_BOX
    review.save()
  }

  def findMasteryByFrameId(def frameId) {
    def currentUserData = getCurrentUserData();
    def allReviews = currentUserData.masteryList as List
    allReviews.find {it.frame.id == frameId}
  }

  UserData getCurrentUserData() {
    return UserData.findByUserName(currentUserName)
  }

  int getMasteryCount() {
    if (!currentUserData?.masteryList) {
      return 0
    }
    return findOrCreateUserData().masteryList.size()
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