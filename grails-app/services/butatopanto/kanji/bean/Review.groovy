package butatopanto.kanji.bean

class Review {
  def currentReview = 0
  def totalCount = 0
  def remainingReviews = []
  def rightReviews = []
  def wrongReviews = []

  def getRemainingCount() {
    remainingReviews.size()
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

