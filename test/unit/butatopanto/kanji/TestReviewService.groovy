package butatopanto.kanji

class TestReviewService {
  final def initialId = "first"
  def lastStartedReview

  void start(Review review) {
    lastStartedReview = review
    review.currentReview = initialId
  }

  def getCurrentFrame(Review review) {
    new Frame(meaning: review.currentReview)
  }
}
