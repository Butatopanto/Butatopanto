package butatopanto.kanji

import butatopanto.kanji.bean.Review

class ReviewService {
  static transactional = true
  def random = new Random()
  def masteryService

  def startChapters(Review review, List chapterNumbers) {
    def frameIds = masteryService.listActiveFrameIdsForChapterList(chapterNumbers)
    start(review, frameIds)
  }

  def startDue(Review review) {
    def dueFrameIds = masteryService.listDueFrameIds()
    start (review, dueFrameIds)
  }

  private def start(Review review, List frameIds) {
    review.remainingFrames = frameIds
    review.totalCount = frameIds.size()
    toNext(review)
  }

  void resolve(Review review, boolean correct) {
    def reviewList = correct ? review.rightReviews : review.wrongReviews
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
    review.remainingFrames.remove((Object) review.currentReview)
    review.currentReview = getRandomId(review)
  }

  private def getRandomId(Review review) {
    if (review.remainingFrames) {
      def index = random.nextInt(review.remainingFrames.size())
      return review.remainingFrames[index]
    }
    null
  }
}