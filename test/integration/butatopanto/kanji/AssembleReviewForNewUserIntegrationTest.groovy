package butatopanto.kanji;


import org.junit.*
import butatopanto.security.User
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase

class AssembleReviewForNewUserIntegrationTest extends GrailsJUnit4ControllerTestCase {

  def springSecurityService

  AssembleReviewForNewUserIntegrationTest() {
    super(AssembleReviewController)
  }

  @Before
  void createUserAndLogIn() {
    new User(username: "Test", password: "Toast").save(failOnError: true)
    springSecurityService.reauthenticate("Test", "Toast")
    controller.assemble()
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

  @Test
  void doesNotSelectInactiveChapterOnLessonAddition() {
    controller.params.id = "1"
    controller.addChapter()
    assertFalse(controller.session.chapters[0].selected)
  }

  @Test
  void doesNotActiveInactiveChapterOnLessonAddition() {
    controller.params.id = "1"
    controller.addChapter()
    assertFalse(controller.session.chapters[0].active)
  }
}