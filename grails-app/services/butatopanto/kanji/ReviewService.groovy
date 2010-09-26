package butatopanto.kanji

import butatopanto.kanji.bean.Review

class ReviewService {
  def random = new Random()

  def start(Review review) {
    review.remainingReviews = Frame.list()*.id
    review.totalCount = review.remainingReviews.size()
    toNext(review)
  }

  void resolve(Review review, def correct) {
    def reviewList = correct ? review.rightReviews : review.wrongReviews
    reviewList.add(review.currentReview)
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