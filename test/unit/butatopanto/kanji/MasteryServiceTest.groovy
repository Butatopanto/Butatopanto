package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test
import butatopanto.security.UserServiceObjectMother

class MasteryServiceTest extends GrailsJUnit4TestCase {

  private MasteryService service = new MasteryService()
  private UserServiceObjectMother userServiceObjectMother = new UserServiceObjectMother()
  private MasteryQueryServiceObjectMother masteryQueryServiceObjectMother = new MasteryQueryServiceObjectMother()

  @Before
  void mockUserService() {
    service.userService = userServiceObjectMother.service
    masteryQueryServiceObjectMother.queryFromHeisigUserData UserServiceObjectMother.defaultUserName
    service.masteryQueryService = masteryQueryServiceObjectMother.service
  }

  @Before
  void mockDomain() {
    mockDomain MasteryOfFrame
    mockDomain Frame, [new Frame(id: 1, keyword: 'first', chapter: 1), new Frame(id: 2, keyword: 'second', chapter: 2), new Frame(id: 3, keyword: 'third', chapter: 2)]
    mockDomain HeisigUser
  }

  @Test
  void createsNonExistingUserDataWhenAddingFrameReviews() {
    userServiceObjectMother.setEnsuredHeisigUserWillBeCreated()
    service.activateChapter(1)
    assertNotNull "No Heisig User found for user", currentHeisigUser
  }

  @Test
  void addsMasteryForSingleFrameToUserData() {
    userServiceObjectMother.setEnsuredHeisigUserWillBeCreated()
    service.activateChapter(1)
    assertHasMasterySortedByMeaning(['first'])
  }

  @Test
  void addsMasteryForMultipleFramesToUserData() {
    userServiceObjectMother.setEnsuredHeisigUserWillBeCreated()
    service.activateChapter(2)
    assertHasMasterySortedByMeaning(['second', 'third'])
  }

  @Test
  void addsFrameToExistingCurrentUserData() {
    userServiceObjectMother.setEnsuredCurrentHeisigUserExists()
    service.activateChapter(1)
    assertHasMasterySortedByMeaning(['first'])
  }

  @Test
  void retainsMasteryOnRepeatedAddition() {
    userServiceObjectMother.setEnsuredCurrentHeisigUserExists()
    service.activateChapter(1)
    MasteryOfFrame mastery = (currentHeisigUser.masteryList as List)[0]
    mastery.passed = 10
    mastery.save()
    service.activateChapter(1)
    assertEquals 10, (currentHeisigUser.masteryList as List)[0].passed
  }

  @Test
  void hasOneMasteryWithActiveFirstChapter() {
    userServiceObjectMother.setEnsuredCurrentHeisigUserExists()
    service.activateChapter(1)
    assertEquals 1, service.getMasteryCount()
  }

  private def assertHasMasterySortedByMeaning(expected) {
    assertEquals(expected, currentHeisigUser.masteryList.collect({ it.frame.keyword }).sort())
  }

  private def getCurrentHeisigUser() {
    userServiceObjectMother.service.currentHeisigUser
  }
}