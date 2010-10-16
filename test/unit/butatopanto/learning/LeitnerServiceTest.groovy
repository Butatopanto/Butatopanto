package butatopanto.learning;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test

class LeitnerServiceTest extends GrailsJUnit4TestCase {

  LeitnerService service = new LeitnerService()
  def mastery = [:]

  @Test
  void putsMasteryInFirstBoxOnFailure() {
    service.answeredWrong(mastery)
    assertEquals(1, mastery.box)
  }

  @Test
  void putsMasteryInFollowupBoxWhenPassingDueMastery() {
    mastery.box = 1
    mastery.lastUpdated = new Date()
    service.answeredRight(mastery)
    assertEquals(2, mastery.box)
  }

  @Test
  void retainsBoxForUndueMastery() {
    mastery.box = 3
    mastery.lastUpdated = new Date()
    service.answeredRight(mastery)
    assertEquals(3, mastery.box)
  }

  @Test
  void recognizesMasteryInBoxTwoAsDueAfterThreeDaysFromLastReview() {
    mastery.box = 2
    setLastReviewDateANumberOfDaysAgo(2)
    assertFalse service.isDue(mastery)
    setLastReviewDateANumberOfDaysAgo(3)
    assertTrue service.isDue(mastery)
  }

  @Test
  void switchesDueStateAtMidnight() {
    mastery.box = 2
    mastery.lastUpdated = new Date(2010, 11, 9, 23, 59)
    service.calendar = [getToday: {
      new Date(2010, 11, 12, 0, 1)
    }]
    assertTrue service.isDue(mastery)
  }

  @Test
  void recognizesMasteryInBoxThreeAsDueAfterSevenDaysFromLastReview() {
    mastery.box = 3
    setLastReviewDateANumberOfDaysAgo(6)
    assertFalse service.isDue(mastery)
    setLastReviewDateANumberOfDaysAgo(7)
    assertTrue service.isDue(mastery)
  }

  @Test
  void recognizesMasteryInBoxFourAsDueAfter14DaysFromLastReview() {
    mastery.box = 4
    setLastReviewDateANumberOfDaysAgo(13)
    assertFalse service.isDue(mastery)
    setLastReviewDateANumberOfDaysAgo(14)
    assertTrue service.isDue(mastery)
  }

  @Test
  void recognizesMasteryInBoxFiveAsDueAfter30DaysFromLastReview() {
    mastery.box = 5
    setLastReviewDateANumberOfDaysAgo(29)
    assertFalse service.isDue(mastery)
    setLastReviewDateANumberOfDaysAgo(30)
    assertTrue service.isDue(mastery)
  }

  @Test
  void recognizesMasteryInBoxSixAsDueAfter60DaysFromLastReview() {
    mastery.box = 6
    setLastReviewDateANumberOfDaysAgo(59)
    assertFalse service.isDue(mastery)
    setLastReviewDateANumberOfDaysAgo(60)
    assertTrue service.isDue(mastery)
  }

  @Test
  void recognizesMasteryInBoxSevenAsDueAfter120DaysFromLastReview() {
    mastery.box = 7
    setLastReviewDateANumberOfDaysAgo(119)
    assertFalse service.isDue(mastery)
    setLastReviewDateANumberOfDaysAgo(120)
    assertTrue service.isDue(mastery)
  }

  @Test
  void recognizesMasteryInBoxEightAsDueAfter240DaysFromLastReview() {
    mastery.box = 8
    setLastReviewDateANumberOfDaysAgo(239)
    assertFalse service.isDue(mastery)
    setLastReviewDateANumberOfDaysAgo(240)
    assertTrue service.isDue(mastery)
  }

  @Test
  void retainsBoxForDueMasteryOfHighestBox() {
    mastery.box = 8
    setLastReviewDateANumberOfDaysAgo(240)
    service.answeredRight(mastery)
    assertEquals(8, mastery.box)
  }

  private void setLastReviewDateANumberOfDaysAgo(int numberOfDays) {
    def today = new Date()
    mastery.lastUpdated = today.minus(numberOfDays)
  }
}