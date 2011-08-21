package butatopanto.learning

class LeitnerService {

  static transactional = true
  static int FIRST_BOX = 1
  static int LAST_BOX = 8
  private static def expirationIntervalByBox = [1: 0, 2: 3, 3: 7, 4: 14, 5: 30, 6: 60, 7: 120, 8: 240]
  def calendar = new Calendar()

  void answeredWrong(mastery) {
    moveToFirstBox(mastery)
  }

  void answeredRight(mastery) {
    if (isDue(mastery)) {
      moveCardToNextBox(mastery)
    }
  }

  int getExpirationIntervalForBox(int number) {
    expirationIntervalByBox.get(number)
  }

  boolean isDue(mastery) {
    return mastery.dueDate <= calendar.today
  }

  public def updateDueDateForMasteryAndReviewDate(mastery, Date reviewDate) {
    int numberOfDaysUntilDue = expirationIntervalByBox[mastery.box]
    mastery.dueDate = reviewDate.plus(numberOfDaysUntilDue)
  }

  private def moveToFirstBox(mastery) {
    mastery.box = FIRST_BOX
    mastery.dueDate = calendar.today
  }

  private def moveCardToNextBox(mastery) {
    mastery.box = Math.min(mastery.box + 1, LAST_BOX)
    updateDueDateForMasteryAndReviewDate(mastery, calendar.today)
  }
}