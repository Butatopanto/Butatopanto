package butatopanto.kanji

import butatopanto.security.User
import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test
import butatopanto.sharedtest.TestCalendar

class MasteryServiceIntegrationTest extends GrailsJUnit4TestCase {

  private def userName = "Test"
  MasteryService masteryService
  def springSecurityService
  def calendar = new TestCalendar()

  @Before
  void initializeCalendar() {
    masteryService.calendar = calendar
    masteryService.leitnerService.calendar = calendar
  }

  @Before
  void createUserAndLogIn() {
    new User(username: userName, password: "Toast").save(failOnError: true)
    springSecurityService.reauthenticate(userName, "Toast")
  }

  @Test
  void addsMasteryToHeisigUser() {
    masteryService.activateChapter(1)
    HeisigUser userData = HeisigUser.findByUserName(userName)
    assertFalse userData.masteryList.isEmpty()
  }

  @Test
  void setsDueDateOnTodayForActivatedFrame() {
    masteryService.activateChapter 1
    HeisigUser userData = HeisigUser.findByUserName(userName)
    assertEquals calendar.today, (userData.masteryList as List).get(0).dueDate
  }

  @Test
  void increasesPassedCountOnRightAnswer() {
    masteryService.activateChapter(1)
    def mastery = findMasteryForFrameOne()
    mastery.passed = 4
    mastery.save(failOnError: true)
    masteryService.answerRight(1)
    assertEquals(findMasteryForFrameOne().passed, 5)
  }

  @Test
  void increasesBoxOnRightAnswer() {
    masteryService.activateChapter(1)
    def mastery = findMasteryForFrameOne()
    def dueForBoxOne = calendar.today.minus(20)
    mastery.box = 1
    mastery.dueDate = dueForBoxOne
    mastery.save(failOnError: true)
    masteryService.answerRight(1)
    assertEquals(2, findMasteryForFrameOne().box)
  }

  @Test
  void increasesFailedCountOnWrongAnswer() {
    masteryService.activateChapter(1)
    def mastery = findMasteryForFrameOne()
    mastery.failed = 4
    mastery.save(failOnError: true)
    masteryService.answerWrong(1)
    assertEquals(findMasteryForFrameOne().failed, 5)
  }

  @Test
  void resetsBoxOnWrongAnswer() {
    masteryService.activateChapter(1)
    def mastery = findMasteryForFrameOne()
    mastery.box = 7
    mastery.save(failOnError: true)
    masteryService.answerWrong(1)
    assertEquals(findMasteryForFrameOne().box, 1)
  }

  @Test
  void hasNoActiveFramesWithoutCurrentUserData() {
    assertEquals([], masteryService.listActiveFrameIdsForChapter(1))
  }

  @Test
  void findsSaveMasteryById() {
    HeisigUser heisigUser = new HeisigUser(userName: userName).save(flush: true, failOnError: true)
    heisigUser.addToMasteryList(new MasteryOfFrame(frame: Frame.get(1), dueDate: calendar.today))
    MasteryOfFrame savedMastery = (heisigUser.masteryList as List).get(0)
    heisigUser.addToMasteryList(new MasteryOfFrame(frame: Frame.get(2), dueDate: calendar.today))
    MasteryOfFrame foundMastery = masteryService.findMasteryByFrameId(1)
    assertEquals savedMastery.id, foundMastery.id
  }

  private MasteryOfFrame findMasteryForFrameOne() {
    return masteryService.findMasteryByFrameId(1)
  }
}