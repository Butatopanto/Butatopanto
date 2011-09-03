package butatopanto.learning;

import butatopanto.sharedtest.*
import org.junit.*

class LeitnerServiceForMasteryInBoxOneTest extends GrailsJUnit4TestCase {

  LeitnerService service = new LeitnerService()
  TestCalendar calendar = new TestCalendar()
  def mastery = [box: 1]

  @Before
  void initCalendarWithAnyDate() {
    service.calendar = calendar
  }

  @Before
  void setMasteryDueOnToday() {
    mastery.lastUpdated = calendar.today
    mastery.dueDate = calendar.today
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
  void putsMasteryInSecondBoxOnRightAnswer() {
    service.answeredRight(mastery)
    assertEquals 2, mastery.box
  }

  @Test
  void setDueDateTwoDaysInTheFutureOnRightAnswer() {
    service.answeredRight(mastery)
    assertEquals calendar.today.plus(3), mastery.dueDate
  }
}