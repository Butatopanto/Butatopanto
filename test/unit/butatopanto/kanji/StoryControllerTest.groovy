package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class StoryControllerTest extends GrailsJUnit4ControllerTestCase {

  StoryControllerTest() {
    super(StoryController)
  }

  private def savedStoriesByFrameId = [:]
  private def deletedStories = []

  @Before
  void mockStoryForFrame() {
    controller.storyService = [
            findStoryTextByFrameId: { frameId ->
              "Story for $frameId"
            },
            saveStory: { Long frameId, String storyText ->
              savedStoriesByFrameId[frameId] = storyText
            },
            deleteStory: {frameId -> deletedStories.add(frameId)}
    ]
    mockDomain Frame, [new Frame(id: 1, number: 1)]
  }

  @Test
  void redirectsToUnknownOnShowWithNoId() {
    controller.show()
    assertEquals "unknown", controller.redirectArgs.action
  }

  @Test
  void redirectsToUnknownOnShowWithUnknownId() {
    controller.params.id = "2"
    controller.show()
    assertEquals "unknown", controller.redirectArgs.action
  }

  @Test
  void returnsStoryTextOfKnownFrame() {
    controller.params.id = "1"
    def values = controller.show()
    assertEquals "Story for 1", values.storyText
  }

  @Test
  void forwardsUriToShowAfterSave() {
    controller.params.id = "1"
    controller.params.uriToShowAfterSave = "/newUri"
    def values = controller.show()
    assertEquals "/newUri", values.uriToShowAfterSave
  }

  @Test
  void redirectsToUnknownOnSaveWithNoId() {
    controller.save()
    assertEquals "unknown", controller.redirectArgs.action
  }

  @Test
  void redirectsToUnknownOnSaveWithUnknownId() {
    controller.params.id = "2"
    controller.save()
    assertEquals "unknown", controller.redirectArgs.action
  }

  @Test
  void savesStoryFromParametersForKnownId() {
    controller.params.id = "1"
    controller.params.storyText = "The new story"
    controller.save()
    assertEquals "The new story", savedStoriesByFrameId[1L]
  }


  @Test
  void deletesStoryWithEmptyText() {
    controller.params.id = "1"
    controller.params.storyText = ""
    controller.save()
    assertEquals([1L], deletedStories)
  }

  @Test
  void doesNotSaveStoryWithEmptyText() {
    controller.params.id = "1"
    controller.params.storyText = ""
    controller.save()
    assertEquals([:], savedStoriesByFrameId)
  }

  @Test
  void redirectsToGivenUrlAfterSuccessfulSave() {
    controller.params.id = "1"
    controller.params.storyText = "The new story"
    controller.params.uriToShowAfterSave = "/newUri"
    controller.save()
    assertEquals([uri: "/newUri"], controller.redirectArgs)
  }

  @Test
  void doesNotRedirectOnUnknown() {
    controller.unknown()
    assertEquals([:], controller.redirectArgs)
  }

  @Test
  void doesNotCreateParametersForUnknown() {
    assertNull controller.unknown()
  }
}