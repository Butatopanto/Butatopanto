package butatopanto.kanji

import butatopanto.security.UserServiceObjectMother
import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class StoryServiceTest extends GrailsJUnit4TestCase {

  private StoryService service = new StoryService()
  private UserServiceObjectMother userServiceMother = new UserServiceObjectMother()

  @Before
  void mockUserService() {
    service.userService = userServiceMother.service
  }

  @Before
  void mockDomain() {
    mockDomain HeisigUser
    mockDomain Story
    mockDomain Frame, [new Frame(id: 1, keyword: 'first'), new Frame(id: 2, keyword: 'second'), new Frame(id: 3, keyword: 'third')]
  }

  @Test
  void findsNoStoryWithoutUserData() {
    assertNull service.findStoryTextByFrameId(1)
  }

  @Test
  void findsStoryForFrameInUserData() {
    def userData = userServiceMother.setCurrentHeisigUserExists()
    userData.addToStoryList(new Story(frame: Frame.get(1), text: "Erste Geschichte"))
    assertEquals "Erste Geschichte", service.findStoryTextByFrameId(1)
  }

  @Test
  void findsNoStoryInEmptyUserData() {
    def userData = userServiceMother.setCurrentHeisigUserExists()
    assertNull service.findStoryTextByFrameId(1)
  }

  @Test
  void removesDeletedStoryFromUserData() {
    userServiceMother.setEnsuredHeisigUserWillBeCreated()
    def userData = userServiceMother.setEnsuredCurrentHeisigUserExists()
    userData.addToStoryList(new Story(frame: Frame.get(1), text: "Erste Geschichte"))
    service.deleteStory 1
    assertNull service.findStoryTextByFrameId(1)
  }

  @Test
  void ignoresMissingStoryForDeletion() {
    userServiceMother.setEnsuredHeisigUserWillBeCreated()
    def userData = userServiceMother.setEnsuredCurrentHeisigUserExists()
    service.deleteStory 1
  }

  @Test
  void findsNoStoryWithPresentStoriesForOtherIds() {
    def userData = userServiceMother.setCurrentHeisigUserExists()
    userData.addToStoryList(new Story(frame: Frame.get(1), text: "Erste Geschichte"))
    assertNull service.findStoryTextByFrameId(2)
  }

  @Test
  void savesNewStoryInUserDataCreatedByService() {
    userServiceMother.setEnsuredHeisigUserWillBeCreated()
    service.saveStory(2, "Eine neue Geschichte")
    assertEquals "Eine neue Geschichte", service.findStoryTextByFrameId(2)
  }

  @Test
  void savesNewStoryInExistingUserData() {
    userServiceMother.setEnsuredCurrentHeisigUserExists()
    service.saveStory(2, "Eine neue Geschichte")
    assertEquals "Eine neue Geschichte", service.findStoryTextByFrameId(2)
  }

  @Test
  void updatesExistingStoryInExistingUserData() {
    def userData = userServiceMother.setEnsuredCurrentHeisigUserExists()
    userData.addToStoryList(new Story(frame: Frame.get(2), text: "Die alte Geschichte"))
    service.saveStory(2, "Eine neue Geschichte")
    assertEquals "Eine neue Geschichte", service.findStoryTextByFrameId(2)
  }
}