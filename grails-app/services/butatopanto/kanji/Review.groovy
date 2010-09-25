package butatopanto.kanji

class Review {
  def remainingIds
  def currentId
  def totalFrameCount
  def correctReviews = []
  def incorrectReviews = []

  def getRemainingFrameCount() {
    remainingIds.size()
  }

  def getCorrectReviewCount() {
    correctReviews.size()
  }

  def getIncorrectReviewCount() {
    incorrectReviews.size()
  }

  def getReviewedFrameCount() {
    getTotalFrameCount() - getRemainingFrameCount()
  }
}

