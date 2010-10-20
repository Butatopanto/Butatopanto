package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test
import butatopanto.security.UserServiceObjectMother

class MasteryServiceTest extends GrailsJUnit4TestCase {

  private MasteryService service = new MasteryService()
  private UserServiceObjectMother userServiceObjectMother = new UserServiceObjectMother()

  @Before
  void mockUserService() {
    service.userService = userServiceObjectMother.service
    service.masteryQueryService = [
      listMastery: {
        HeisigUser.findByUserName(UserServiceObjectMother.defaultUserName).masteryList as List
      },
      findMasteryByFrameId: {  frameId ->
        def masteryList = HeisigUser.findByUserName(UserServiceObjectMother.defaultUserName).masteryList as List
        masteryList.find {frameId == it.frame.id}
      }
    ]
  }

  @Before
  void mockDomain() {
    mockDomain MasteryOfFrame
    mockDomain Frame, [new Frame(id: 1, meaning: 'first', chapter: 1), new Frame(id: 2, meaning: 'second', chapter: 2), new Frame(id: 3, meaning: 'third', chapter: 2)]
    mockDomain HeisigUser
  }

  @Test
  void createsNonExistingUserDataWhenAddingFrameReviews() {
    userServiceObjectMother.setEnsuredHeisigUserWillBeCreated()
    service.activateLesson(1)
    assertNotNull "No Heisig User found for user", currentHeisigUser
  }

  @Test
  void addsMasteryForSingleFrameToUserData() {
    userServiceObjectMother.setEnsuredHeisigUserWillBeCreated()
    service.activateLesson(1)
    assertHasMasterySortedByMeaning(['first'])
  }

  @Test
  void addsMasteryForMultipleFramesToUserData() {
    userServiceObjectMother.setEnsuredHeisigUserWillBeCreated()
    service.activateLesson(2)
    assertHasMasterySortedByMeaning(['second', 'third'])
  }

  @Test
  void addsFrameToExistingCurrentUserData() {
    userServiceObjectMother.setEnsuredCurrentHeisigUserExists()
    service.activateLesson(1)
    assertHasMasterySortedByMeaning(['first'])
  }

  @Test
  void retainsMasteryOnRepeatedAddition() {
    userServiceObjectMother.setEnsuredCurrentHeisigUserExists()
    service.activateLesson(1)
    MasteryOfFrame mastery = (currentHeisigUser.masteryList as List)[0]
    mastery.passed = 10
    mastery.save()
    service.activateLesson(1)
    assertEquals 10, (currentHeisigUser.masteryList as List)[0].passed
  }

  @Test
  void listsAllFrameIdAsDueForMasteryRecognizedByLeitnerService() {
    userServiceObjectMother.setEnsuredCurrentHeisigUserExists()
    service.activateLesson(2)
    MasteryOfFrame dueMastery = (currentHeisigUser.masteryList as List)[0]
    service.leitnerService = [isDue: {it == dueMastery}]
    assertEquals([dueMastery.frame.id], service.listDueFrameIds())
  }

  private def assertHasMasterySortedByMeaning(expected) {
    assertEquals(expected, currentHeisigUser.masteryList.collect({ it.frame.meaning }).sort())
  }

  private def getCurrentHeisigUser() {
    userServiceObjectMother.service.currentHeisigUser
  }
}