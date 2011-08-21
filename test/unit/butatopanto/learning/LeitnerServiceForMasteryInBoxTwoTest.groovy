package butatopanto.learning;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test
import butatopanto.sharedtest.TestCalendar

class LeitnerServiceForMasteryInBoxTwoTest extends GrailsJUnit4TestCase {

  LeitnerService service = new LeitnerService()
  TestCalendar calendar = new TestCalendar()
  def mastery = [box: 2]

  @Before
  void initCalendarWithAnyDate() {
    service.calendar = calendar
  }

  @Test
  void putsMasteryInFirstBoxOnWrongAnswer() {
    service.answeredWrong(mastery)
    assertEquals 1, mastery.box
  }

  @Test
  void setsDueDateToTodayOnWrongAnswer() {
    service.answeredWrong(mastery)
    assertEquals calendar.today, mastery.dueDate
  }

  @Test
  void putsDueMasteryInThirdBoxOnRightAnswer() {
    setMasteryDueSinceToday()
    service.answeredRight(mastery)
    assertEquals 3, mastery.box
  }

  @Test
  void setDueDateForDueMasterySevenDaysInTheFutureOnRightAnswer() {
    setMasteryDueSinceToday()
    service.answeredRight(mastery)
    assertEquals calendar.today.plus(7), mastery.dueDate
  }

  @Test
  void doesNotChangeBoxOfUndueMasteryOnRightAnswer() {
    setMasteryDueSinceTomorrow()
    service.answeredRight(mastery)
    assertEquals 2, mastery.box
  }

  private void setMasteryDueSinceToday() {
    mastery.dueDate = calendar.today
    mastery.lastUpdated = calendar.today.minus(3)
  }

  private void setMasteryDueSinceTomorrow() {
    mastery.dueDate = calendar.today.plus(1)
    mastery.lastUpdated = calendar.today.minus(2)
  }
}