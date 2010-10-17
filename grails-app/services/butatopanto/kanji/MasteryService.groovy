package butatopanto.kanji

import butatopanto.request.ListRequester

class MasteryService {

  static transactional = true
  def userService
  def leitnerService

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

  def listDueFrameIds() {
    listActiveFrameIds().findAll {
      def mastery = findMasteryByFrameId(it)
      leitnerService.isDue(mastery)
    }
  }

  def listDueFrameIdsForChapter(int chapterNumber) {
    listActiveFrameIdsForChapterList([chapterNumber]).findAll {
      def mastery = findMasteryByFrameId(it)
      leitnerService.isDue(mastery)
    }
  }

  def listActiveFrameIdsForChapterList(List chapterNumbers) {
    def masteryList = listMastery()
    def relevantMastery = masteryList.findAll {chapterNumbers.contains(it.frame.lesson)}
    relevantMastery.collect {it.frame.id}
  }

  def listActiveFrameIds() {
    def masteryList = listMastery()
    masteryList.collect {it.frame.id}
  }

  List listMastery(String sortAttribute, String order, int offset, int max) {
    List masteryList = listMastery()
    ListRequester.From(masteryList)."sorted${order}By"(sortAttribute).startingFromIndex(offset).getAtMostElements(max)
  }

  def listMastery() {
    if (!currentUserData?.masteryList) {
      return []
    }
    currentUserData.masteryList as List
  }

  def answerRight(def frameId) {
    def mastery = findMasteryByFrameId(frameId)
    mastery.passed += 1
    leitnerService.answeredRight(mastery)
    mastery.save()
  }

  def answerWrong(def frameId) {
    def mastery = findMasteryByFrameId(frameId)
    mastery.failed += 1
    leitnerService.answeredWrong(mastery)
    mastery.save()
  }

  def findMasteryByFrameId(def frameId) {
    def currentUserData = getCurrentUserData();
    def masteryList = currentUserData.masteryList as List
    masteryList.find {it.frame.id == frameId}
  }

  HeisigUser getCurrentUserData() {
    return HeisigUser.findByUserName(currentUserName)
  }

  int getMasteryCount() {
    if (!currentUserData?.masteryList) {
      return 0
    }
    return findOrCreateUserData().masteryList.size()
  }

  private def findOrCreateUserData() {
    if (!currentUserData) {
      new HeisigUser(userName: currentUserName).save()
    }
    currentUserData
  }

  private String getCurrentUserName() {
    userService.currentUser.username
  }
}