package butatopanto.kanji

import butatopanto.kanji.bean.Review

class TestReviewService {
  final def initialId = "first"
  def lastStartedReview
  java.util.List reviewedChapters

  void start(Review review, List chapterNumbers) {
    this.reviewedChapters = chapterNumbers
    this.lastStartedReview = review
    review.currentReview = initialId
  }

  def getCurrentFrame(Review review) {
    new Frame(meaning: review.currentReview)
  }
}
