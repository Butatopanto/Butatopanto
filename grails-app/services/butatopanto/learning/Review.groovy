package butatopanto.learning

class Review {
  def currentReview = 0
  def totalCount = 0
  def remainingIds = []
  def rightIds = []
  def wrongIds = []

  boolean isFinished() {
    !remainingIds
  }

  def getRemainingCount() {
    remainingIds.size()
  }

  def getRightCount() {
    rightIds.size()
  }

  def getWrongCount() {
    wrongIds.size()
  }

  def getReviewedCount() {
    getTotalCount() - getRemainingCount()
  }
}

