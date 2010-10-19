package butatopanto.kanji

import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test
import butatopanto.security.UserServiceObjectMother

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
    mockDomain Frame, [new Frame(id: 1, meaning: 'first'), new Frame(id: 2, meaning: 'second'), new Frame(id: 3, meaning: 'third')]
  }

  @Test
  void findsNoStoryWithoutUserData() {
    assertNull service.findStoryTextByFrameId(1)
  }

  @Test
  void findsStoryForFrameInUserData() {
    def userData = userServiceMother.setCurrentUserDataExists()
    userData.addToStoryList(new Story(frame: Frame.get(1), text: "Erste Geschichte"))
    assertEquals "Erste Geschichte", service.findStoryTextByFrameId(1)
  }

  @Test
  void findsNoStoryInEmptyUserData() {
    def userData = userServiceMother.setCurrentUserDataExists()
    assertNull service.findStoryTextByFrameId(1)
  }

  @Test
  void findsNoStoryWithPresentStoriesForOtherIds() {
    def userData = userServiceMother.setCurrentUserDataExists()
    userData.addToStoryList(new Story(frame: Frame.get(1), text: "Erste Geschichte"))
    assertNull service.findStoryTextByFrameId(2)
  }

  @Test
  void savesNewStoryInUserDataCreatedByService() {
    userServiceMother.setEnsuredUserDataWillBeCreated()
    service.saveStory(2, "Eine neue Geschichte")
    assertEquals "Eine neue Geschichte", service.findStoryTextByFrameId(2)
  }

  @Test
  void savesNewStoryInExistingUserData() {
    userServiceMother.setEnsuredCurrentUserDataExists()
    service.saveStory(2, "Eine neue Geschichte")
    assertEquals "Eine neue Geschichte", service.findStoryTextByFrameId(2)
  }

  @Test
  void updatesExistingStoryInExistingUserData() {
    def userData = userServiceMother.setEnsuredCurrentUserDataExists()
    userData.addToStoryList(new Story(frame: Frame.get(2), text: "Die alte Geschichte"))
    service.saveStory(2, "Eine neue Geschichte")
    assertEquals "Eine neue Geschichte", service.findStoryTextByFrameId(2)
  }
}