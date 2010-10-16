package butatopanto.kanji

import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class StoryServiceTests extends GrailsJUnit4TestCase {

  private StoryService service = new StoryService()
  private userService = [:]

  @Before
  void mockUserService() {
    service.userService = userService
  }

  @Before
  void mockDomain() {
    mockDomain UserData
    mockDomain MasteryOfFrame
    mockDomain Frame, [new Frame(id: 1, meaning: 'first'), new Frame(id: 2, meaning: 'second'), new Frame(id: 3, meaning: 'third')]
  }

  @Test
  void findsNoStoryWithoutUserData() {
    assertNull service.findStoryByFrameId(1)
  }

  @Test
  void findsStoryForFrameInUserData() {
    UserData userData = new UserData(userName: "The User").save()
    userService["currentUserData"] = userData
    userData.addToStoryList(new Story(frame: Frame.get(1), text: "Erste Geschichte"))
    assertEquals "Erste Geschichte", service.findStoryByFrameId(1)
  }

  @Test
  void findsNoStoryInEmptyUserData() {
    userService["currentUserData"] = new UserData(userName: "The User").save()
    assertNull service.findStoryByFrameId(1)
  }

  @Test
  void findsNoStoryWithPresentStoriesForOtherIds() {
    def userData = new UserData(userName: "The User").save()
    userService["currentUserData"] = userData
    userData.addToStoryList(new Story(frame: Frame.get(1), text: "Erste Geschichte"))
    assertNull service.findStoryByFrameId(2)
  }
}