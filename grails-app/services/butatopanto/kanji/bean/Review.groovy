package butatopanto.kanji.bean

class Review {
  def currentReview = 0
  def totalCount = 0
  def remainingFrames = []
  def rightReviews = []
  def wrongReviews = []

  def getRemainingCount() {
    remainingFrames.size()
  }

  def getRightCount() {
    rightReviews.size()
  }

  def getWrongCount() {
    wrongReviews.size()
  }

  def getReviewedCount() {
    getTotalCount() - getRemainingCount()
  }
}

