package butatopanto.kanji;


import butatopanto.security.User
import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class MasteryServiceMasteryRemovalTest extends GrailsJUnit4TestCase {

  private MasteryService service = new MasteryService()
  private def userName = "the user"
  butatopanto.kanji.HeisigUser heisigUser

  @Before
  void mockUserService() {
    service.userService = [currentUser: new User(username: userName)]
  }

  @Before
  void mockDomain() {
    mockDomain Frame, [new Frame(id: 1, meaning: 'first', lesson: 1), new Frame(id: 2, meaning: 'second', lesson: 2), new Frame(id: 3, meaning: 'third', lesson: 2)]
    this.heisigUser = new HeisigUser(userName: userName)
    mockDomain HeisigUser, [heisigUser]
  }

  @Test
  void removesMasteryForSingleFrameFromUserData() {
    createMastery([Frame.findByLesson(1)])
    service.deactivateLesson(1)
    assertHasNoReviews()
  }

  @Test
  void removesMasteryForMultipleFramesFromUserData() {
    createMastery(Frame.findAllByLesson(2))
    service.deactivateLesson(2)
    assertHasNoReviews()
  }

  @Test
  void removesMasteryOnlyForGivenLesson() {
    createMastery(Frame.list())
    service.deactivateLesson(2)
    assertHasMasterySortedByMeaning(['first'])
  }

  private def createMastery(def frames) {
    def reviews = frames.collect {
      new MasteryOfFrame(frame: it)
    }
    mockDomain MasteryOfFrame, reviews
    reviews.each {
      heisigUser.addToMasteryList(it)
    }
  }

  private HeisigUser createUserDataWithUserName() {
    new HeisigUser(userName: userName).save()
  }

  private def assertHasMasterySortedByMeaning(expected) {
    def user = HeisigUser.findByUserName(userName)
    assertEquals(expected, user.masteryList.collect({ it.frame.meaning }).sort())
  }

  private def assertHasNoReviews() {
    assertTrue heisigUser.masteryList.isEmpty()
  }
}