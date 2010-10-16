package butatopanto.kanji

import butatopanto.learning.Review

class ReviewService {
  static transactional = true
  def random = new Random()
  def masteryService

  def startChapters(List chapterNumbers) {
    def frameIds = masteryService.listActiveFrameIdsForChapterList(chapterNumbers)
    start(frameIds)
  }

  def startDue() {
    def dueFrameIds = masteryService.listDueFrameIds()
    start (dueFrameIds)
  }

  private def start(List frameIds) {
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
    toNext(review)
  }

  Frame getCurrentFrame(Review review) {
    review.currentReview == null ? null : Frame.findById(review.currentReview)
  }

  private void toNext(Review review) {
    review.remainingIds.remove((Object) review.currentReview)
    review.currentReview = getRandomId(review)
  }

  private def getRandomId(Review review) {
    if (review.remainingIds) {
      def index = random.nextInt(review.remainingIds.size())
      return review.remainingIds[index]
    }
    null
  }
}