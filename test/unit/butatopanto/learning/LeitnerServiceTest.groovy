package butatopanto.learning;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import butatopanto.sharedtest.TestCalendar
import org.junit.Test
import org.junit.Before

class LeitnerServiceTest extends GrailsJUnit4TestCase {

  TestCalendar calendar = new TestCalendar()
  LeitnerService service = new LeitnerService()
  def mastery = [:]

  @Before
  void initializeCalendar() {
    service.calendar = calendar
  }

  @Before
  void initializeDueDateSoThatMasteryIsDue() {
    mastery.dueDate = calendar.today
  }

  @Test
  void switchesDueStateAtMidnight() {
    mastery.box = 2
    mastery.dueDate = new Date(2010, 11, 9, 23, 59)
    calendar.today = new Date(2010, 11, 10, 0, 1)
    assertTrue service.isDue(mastery)
  }

  @Test
  void setsDueDateIn14DatesForKanjiEnteringBox4() {
    mastery.box = 3
    service.answeredRight(mastery)
    assertEquals(calendar.today.plus(14), mastery.dueDate)
  }

  @Test
  void setsDueDateIn30DaysForKanjiEnteringBox5() {
    mastery.box = 4
    service.answeredRight(mastery)
    assertEquals(calendar.today.plus(30), mastery.dueDate)
  }

  @Test
  void setsDueDateIn60DaysForKanjiEnteringBox6() {
    mastery.box = 5
    service.answeredRight(mastery)
    assertEquals(calendar.today.plus(60), mastery.dueDate)
  }

  @Test
  void setsDueDateIn120DaysForKanjiEnteringBox7() {
    mastery.box = 6
    service.answeredRight(mastery)
    assertEquals(calendar.today.plus(120), mastery.dueDate)
  }

  @Test
  void setsDueDateIn240DaysForKanjiEnteringBox8() {
    mastery.box = 7
    service.answeredRight(mastery)
    assertEquals(calendar.today.plus(240), mastery.dueDate)
  }

  @Test
  void retainsBoxForDueMasteryOfHighestBox() {
    mastery.box = 8
    service.answeredRight(mastery)
    assertEquals(8, mastery.box)
  }
}