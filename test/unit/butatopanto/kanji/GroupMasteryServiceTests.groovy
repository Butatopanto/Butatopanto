package butatopanto.kanji

import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class GroupMasteryServiceTests extends GrailsJUnit4TestCase {

  GroupMasteryService service = new GroupMasteryService()
  TestMasteryQueryService masteryQueryService = new TestMasteryQueryService()
  TestLeitnerService leitnerService = new TestLeitnerService()
  def user1
  def user2

  @Before
  void configureService() {
    service.masteryQueryService = masteryQueryService
    service.leitnerService = leitnerService
  }

  @Before
  void createUsers() {
    user1 = createUser('Urs')
    user2 = createUser('Sandra')
    addMasteriesForUser(user1, 1..3)
    addMasteriesForUser(user2, 1..3)
  }

  @Test
  void findsAllDueFramesForGroupWithSingleUser() {
    def dueMastery = getMastery(user1, 1)
    addDueMasteries([dueMastery])
    assertEquals([2], service.listDueFrameIds([user1]))
  }

  @Test
  void findsAllDueFramesForAllUsersInGroup() {
    def dueMastery1 = getMastery(user1, 0)
    def dueMastery2 = getMastery(user2, 1)
    addDueMasteries([dueMastery1, dueMastery2])
    assertEquals([1, 2], service.listDueFrameIds([user1, user2]))
  }

  @Test
  void filtersDuplicatedFrameNumbers() {
    def dueMastery1 = getMastery(user1, 1)
    def dueMastery2 = getMastery(user2, 1)
    addDueMasteries([dueMastery1, dueMastery2])
    assertEquals([2], service.listDueFrameIds([user1, user2]))
  }


  private def getMastery(user, int masteryIndex) {
    return masteryQueryService.masteriesForUser.get(user)[masteryIndex]
  }

  private def createUser(String username) {
    return [userName: username]
  }

  private void addMasteriesForUser(def user, def masteredFrameNumbers) {
    masteryQueryService.addMasteriesForUser(user, masteredFrameNumbers)
  }

  private void addDueMasteries(def dueMasteries) {
    leitnerService.dueMasteries.addAll dueMasteries
  }

  private class TestMasteryQueryService {

    def masteriesForUser = [:]

    def listMasteriesByUsername(username) {
      masteriesForUser.get(username)
    }

    void addMasteriesForUser(user, masteries) {
      masteriesForUser.put(user, createMasteries(masteries))
    }

    private def createMasteries(numbers) {
      numbers.collect {createMastery it}
    }

    private def createMastery(int number) {
      return new MasteryOfFrame(frame: new Frame(number: number))
    }
  }

  private class TestLeitnerService {

    def dueMasteries = []

    boolean isDue(mastery) {
      dueMasteries.contains(mastery)
    }
  }
}
