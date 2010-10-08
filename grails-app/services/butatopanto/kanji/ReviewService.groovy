package butatopanto.kanji

import butatopanto.kanji.bean.Review

class ReviewService {
  def random = new Random()
  def heisigUserDataService

  def start(Review review, List chapterNumbers) {
    review.remainingReviews = heisigUserDataService.getActiveFrameIdsForChapterList(chapterNumbers)
    review.totalCount = review.remainingReviews.size()
    toNext(review)
  }

  void resolve(Review review, boolean correct) {
    def reviewList = correct ? review.rightReviews : review.wrongReviews
    reviewList.add(review.currentReview)
    if (correct) {
      heisigUserDataService.answerRight(review.currentReview)
    } else {
      heisigUserDataService.answerWrong(review.currentReview)
    }
    toNext(review)
  }

  Frame getCurrentFrame(Review review) {
    review.currentReview == null ? null : Frame.findById(review.currentReview)
  }

  private void toNext(Review review) {
    review.remainingReviews.remove((Object) review.currentReview)
    review.currentReview = getRandomId(review)
  }

  private def getRandomId(Review review) {
    if (review.remainingReviews) {
      def index = random.nextInt(review.remainingReviews.size())
      return review.remainingReviews[index]
    }
    null
  }
}