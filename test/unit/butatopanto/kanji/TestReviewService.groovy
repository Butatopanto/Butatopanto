package butatopanto.kanji

import butatopanto.learning.Review

class TestReviewService {
  final def initialId = "first"
  def lastStartedReview
  java.util.List reviewedChapters

  def startChapters(List chapterNumbers) {
    lastStartedReview = new Review()
    this.reviewedChapters = chapterNumbers
    lastStartedReview.currentReview = initialId
    return lastStartedReview
  }

  def getCurrentFrame(Review review) {
    new Frame(meaning: review.currentReview)
  }
}
