package butatopanto.kanji

class TestReviewService {
  final def initialId = "first"
  def lastStartedReview

  void start(Review review) {
    lastStartedReview = review
    review.currentId = initialId
  }

  def getCurrentFrame(Review review) {
    new Frame(meaning: review.currentId)
  }
}
