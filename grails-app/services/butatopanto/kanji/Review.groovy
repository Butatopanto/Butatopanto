package butatopanto.kanji

class Review {
  def currentId = 0
  def totalFrameCount = 0
  def remainingIds = []
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

