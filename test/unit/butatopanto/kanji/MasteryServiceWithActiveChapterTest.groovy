package butatopanto.kanji;


import butatopanto.security.UserServiceObjectMother
import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class MasteryServiceWithActiveChapterTest extends GrailsJUnit4TestCase {

  private MasteryService service = new MasteryService()
  private UserServiceObjectMother userServiceObjectMother = new UserServiceObjectMother()
  private MasteryQueryServiceObjectMother masteryQueryServiceObjectMother = new MasteryQueryServiceObjectMother()

  @Before
  void activeChapterTwo() {
    mockUserService()
    mockMasteryQueryService()
    mockDomain MasteryOfFrame
    mockDomain Frame, [new Frame(id: 1, chapter: 1), new Frame(id: 2, chapter: 2), new Frame(id: 3, chapter: 2)]
    service.activateChapter(2)
  }

  private void mockUserService() {
    mockDomain HeisigUser
    service.userService = userServiceObjectMother.service
    userServiceObjectMother.setEnsuredCurrentHeisigUserExists()
  }

  private void mockMasteryQueryService() {
    masteryQueryServiceObjectMother.queryFromHeisigUserData UserServiceObjectMother.defaultUserName
    service.masteryQueryService = masteryQueryServiceObjectMother.service
  }
    
  @Test
  void activatedChapterIsHindmost() {
    assertEquals 2, service.getHindmostMasteredChapter()
  }

  @Test
  void hasTwoMasteryInList() {
    assertEquals 2, service.getMasteryCount()
  }

  @Test
  void listsAllActiveMasterySortedDescendingWithoutOffsetAndHighMaximum() {
    assertEquals([3, 2], service.listMastery("frame.id", "Descending", 0, 10).collect {it.frame.id})
  }

  @Test
  void respectsLowMaximumWhenListingMastery() {
    assertEquals([3], service.listMastery("frame.id", "Descending", 0, 1).collect {it.frame.id})
  }

  @Test
  void respectsOffsetWhenListingMastery() {
    assertEquals([2], service.listMastery("frame.id", "Descending", 1, 10).collect {it.frame.id})
  }

  private def assertHasMasterySortedByMeaning(expected) {
    assertEquals(expected, currentHeisigUser.masteryList.collect({ it.frame.keyword }).sort())
  }

  private def getCurrentHeisigUser() {
    userServiceObjectMother.service.currentHeisigUser
  }
}
