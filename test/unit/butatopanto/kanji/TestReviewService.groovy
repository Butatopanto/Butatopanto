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
    new Frame(keyword: review.currentReview)
  }

  def startRange(int from, int to) {
    //nothing here
  }

  def start(def list) {
    def review = new Review()
    review.remainingIds = list
    review
  }
}
