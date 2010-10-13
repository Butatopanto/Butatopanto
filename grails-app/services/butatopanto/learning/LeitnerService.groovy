package butatopanto.learning

class LeitnerService {

  static int FIRST_BOX = 1
  static int LAST_BOX = 8
  static transactional = true
  private def expirationIntervalByBox = [1: 0, 2: 3, 3: 7, 4: 14, 5: 30, 6: 60, 7: 120, 8: 240]
  def calendar = new Calendar()

  void failed(mastery) {
    mastery.box = FIRST_BOX
  }

  void successful(mastery) {
    if (isDue(mastery)) {
      moveCardToNextBox(mastery)
    }
  }

  boolean isDue(mastery) {
    def daysToRemainInCurrentBox = calculateDaysToRemainInCurrentBox(mastery)
    def daysSinceLastReview = calculateDaysSinceLastReview(mastery)
    daysSinceLastReview >= daysToRemainInCurrentBox
  }

  private def calculateDaysToRemainInCurrentBox(mastery) {
    expirationIntervalByBox[mastery.box]
  }

  private def calculateDaysSinceLastReview(mastery) {
    def today = calendar.getToday()
    def lastReviewDate = mastery.lastUpdated
    today.minus(lastReviewDate)
  }

  private def moveCardToNextBox(mastery) {
    mastery.box = Math.min(mastery.box + 1, LAST_BOX)
  }
}