package butatopanto.kanji;

import butatopanto.test.*
import org.junit.*
import butatopanto.security.User

class ManageReviewForNewUserIntegrationTest extends GrailsJUnit4ControllerTestCase {

  def springSecurityService

  ManageReviewForNewUserIntegrationTest() {
    super(ReviewController)
  }

  @Before
  void createUserAndLogIn() {
    new User(username: "Test", password: "Toast").save(failOnError: true)
    springSecurityService.reauthenticate("Test", "Toast")
    controller.manage()
  }

  @Test
  void hasNumberOfFramesForChapter() {
    assertEquals(15, controller.session.chapters[0].totalFrames)
  }

  @Test
  void hasNoDueFramesForChapter() {
    assertEquals(0, controller.session.chapters[0].dueFrameCount)
  }

  @Test
  void hasUnselectedChapters() {
    assertFalse(controller.session.chapters[0].selected)
  }

  @Test
  void hasInactiveChapters() {
    assertFalse(controller.session.chapters[0].active)
  }
}