package butatopanto.kanji

import butatopanto.request.ListRequester

class MasteryService {

  static transactional = true
  def userService
  def masteryQueryService
  def leitnerService

  def activateLesson(def number) {
    def heisigUser = findOrCreateHeisigUser()
    def chapterFrameList = Frame.findAllByLesson(number)
    chapterFrameList.each {
      if (!MasteryOfFrame.findByFrame(it)) {
        heisigUser.addToMasteryList new MasteryOfFrame(frame: it)
      }
    }
  }

  def deactivateLesson(def number) {
    def heisigUser = findOrCreateHeisigUser()
    def chapterFrameList = Frame.findAllByLesson(number)
    chapterFrameList.each {
      def review = MasteryOfFrame.findByFrame(it)
      if (review) {
        heisigUser.removeFromMasteryList review
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
    def masteryList = masteryQueryService.listMasteryForChapterList(chapterNumbers)
    masteryList.collect {it.frame.id}
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
    masteryQueryService.listMastery()
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

  def findMasteryByFrameId(long frameId) {
    masteryQueryService.findMasteryByFrameId(frameId)
  }

  int getMasteryCount() {
    listMastery().size()
  }

  private def findOrCreateHeisigUser() {
    if (!currentUserData) {
      new HeisigUser(userName: currentUserName).save()
    }
    currentUserData
  }

  private HeisigUser getCurrentUserData() {
    return HeisigUser.findByUserName(currentUserName)
  }

  private String getCurrentUserName() {
    def user = userService.currentUser
    user.username
  }
}