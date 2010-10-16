package butatopanto.kanji;


import butatopanto.security.User
import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class MasteryServiceTest extends GrailsJUnit4TestCase {

  private MasteryService service = new MasteryService()
  private def userName = "the user"

  @Before
  void mockUserService() {
    service.userService = [currentUser: new User(username: userName)]
  }

  @Before
  void mockDomain() {
    mockDomain MasteryOfFrame
    mockDomain Frame, [new Frame(id: 1, meaning: 'first', lesson: 1), new Frame(id: 2, meaning: 'second', lesson: 2), new Frame(id: 3, meaning: 'third', lesson: 2)]
    mockDomain UserData
  }

  @Test
  void createsNonExistingUserDataWhenAddingFrameReviews() {
    service.activateLesson(1)
    assertNotNull "No UserData found for user", UserData.findByUserName(userName)
  }

  @Test
  void addsMasteryForSingleFrameToUserData() {
    service.activateLesson(1)
    assertHasMasterySortedByMeaning(['first'])
  }

  @Test
  void addsMasteryForMultipleFramesToUserData() {
    service.activateLesson(2)
    assertHasMasterySortedByMeaning(['second', 'third'])
  }

  @Test
  void addsFrameToExistingCurrentUserData() {
    createUserDataWithUserName()
    service.activateLesson(1)
    assertHasMasterySortedByMeaning(['first'])
  }

  @Test
  void knowsExistingCurrentUserData() {
    UserData userData = createUserDataWithUserName()
    assertEquals userData.id, service.currentUserData.id
  }

  @Test
  void hasNoActiveFramesWithoutCurrentUserData() {
    assertEquals([], service.listActiveFrameIdsForChapter(1))
  }

  @Test
  void hasNoActiveFramesForUserDataWithoutMastery() {
    createUserDataWithUserName()
    assertEquals([], service.listActiveFrameIdsForChapter(1))
  }

  @Test
  void hasActiveFramesForExistingFrameReview() {
    UserData userData = createUserDataWithUserName();
    def reviewedFrame = Frame.get(2)
    userData.addToMasteryList(new MasteryOfFrame(frame: reviewedFrame))
    assertEquals([2], service.listActiveFrameIdsForChapter(reviewedFrame.lesson))
  }

  @Test
  void retainsMasteryOnRepeatedAddition() {
    service.activateLesson(1)
    MasteryOfFrame mastery = (service.currentUserData.masteryList as List)[0]
    mastery.passed = 10
    mastery.save()
    service.activateLesson(1)
    assertEquals 10, (service.currentUserData.masteryList as List)[0].passed
  }

  @Test
  void listsAllFrameIdAsDueForMasteryRecognizedByLeitnerService() {
    service.activateLesson(2)
    MasteryOfFrame dueMastery = (service.currentUserData.masteryList as List)[0]
    service.leitnerService = [isDue: {it == dueMastery}]
    assertEquals([dueMastery.frame.id], service.listDueFrameIds())
  }

  private UserData createUserDataWithUserName() {
    new UserData(userName: userName).save()
  }

  private def assertHasMasterySortedByMeaning(expected) {
    def userData = UserData.findByUserName(userName)
    assertEquals(expected, userData.masteryList.collect({ it.frame.meaning }).sort())
  }
}