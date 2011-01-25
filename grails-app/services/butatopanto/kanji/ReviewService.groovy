package butatopanto.kanji

import butatopanto.learning.Review

class ReviewService {

  static transactional = true
  def random = new Random()
  def masteryService
  def storyService

  Review startChapters(List chapterNumbers) {
    def frameIds = masteryService.listActiveFrameIdsForChapterList(chapterNumbers)
    start frameIds
  }

  Review startDueFrom(List chapterNumbers) {
    def dueFrameIds = masteryService.listDueFramesForChapterList(chapterNumbers)
    start dueFrameIds
  }

  Review startDue() {
    def dueFrameIds = masteryService.listDueFrameIds()
    start dueFrameIds
  }

  Review startRange(int from, int to) {
    start new ArrayList(from..to)
  }

  Review start(List frameIds) {
    Review review = new Review()
    review.remainingIds = frameIds
    review.totalCount = frameIds.size()
    toNext(review)
    return review
  }

  void resolve(Review review, boolean correct) {
    def reviewList = correct ? review.rightIds : review.wrongIds
    reviewList.add(review.currentReview)
    if (correct) {
      masteryService.answerRight(review.currentReview)
    } else {
      masteryService.answerWrong(review.currentReview)
    }
    review.remainingIds.remove((Object) review.currentReview)
  }

  void toNext(Review review) {
    review.currentReview = getRandomId(review)
  }

  Frame getCurrentFrame(Review review) {
    def currentReview = review.currentReview
    Frame.get(currentReview)
  }

  String getCurrentStory(Review review) {
    def currentReview = review.currentReview
    storyService.findStoryTextByFrameId(currentReview) ?: ""
  }

  private def getRandomId(Review review) {
    if (review.remainingIds) {
      def index = random.nextInt(review.remainingIds.size())
      return review.remainingIds[index]
    }
    null
  }

  def resolveAndAdvance(Review review, Boolean correct) {
    resolve review, correct
    toNext review
    getCurrentFrame review
  }
}