package butatopanto.kanji;


import butatopanto.security.User
import butatopanto.test.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class ManageReviewForExperiencedUserIntegrationTest extends GrailsJUnit4ControllerTestCase {

  def springSecurityService
  def masteryService

  ManageReviewForExperiencedUserIntegrationTest() {
    super(ReviewController)
  }

  @Before
  void createUserAndLogIn() {
    new User(username: "Test", password: "Toast").save(failOnError: true)
    springSecurityService.reauthenticate("Test", "Toast")
    masteryService.activateLesson(1)
    controller.manage()
  }

  @Test
  void hasNumberOfFramesForChapter() {
    assertEquals(15, controller.session.chapters[0].totalFrames)
  }

  @Test
  void hasDueFramesForPreviouslyAddedLesson() {
    assertEquals(15, controller.session.chapters[0].dueFrameCount)
  }

  @Test
  void marksActiveChapterAsSelected() {
    assertTrue(controller.session.chapters[0].selected)
  }

  @Test
  void marksActiveChapter() {
    assertTrue(controller.session.chapters[0].active)
  }
}