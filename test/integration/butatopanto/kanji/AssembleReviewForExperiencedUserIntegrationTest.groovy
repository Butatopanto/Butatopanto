package butatopanto.kanji;


import butatopanto.security.User
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class AssembleReviewForExperiencedUserIntegrationTest extends GrailsJUnit4ControllerTestCase {

  def springSecurityService

  AssembleReviewForExperiencedUserIntegrationTest() {
    super(AssembleReviewController)
  }

  @Before
  void createUserWithOneFreshlyActivatedChapterAndLogIn() {
    new User(username: "Test", password: "Toast").save(failOnError: true)
    springSecurityService.reauthenticate("Test", "Toast")
    controller.masteryService.activateChapter(1)
    controller.assemble()
  }

  @Test
  void hasNumberOfFramesForActivatedChapter() {
    assertEquals(15, getActivatedChapter().totalFrames)
  }

  @Test
  void hasAllKanjiDueForActivatedChapter() {
    assertEquals(15, getActivatedChapter().dueFrameCount)
  }

  @Test
  void activatedChapterIsNotSelected() {
    assertFalse(getActivatedChapter().selected)
  }

  @Test
  void activatedChapterIsActive() {
    assertTrue(getActivatedChapter().active)
  }

    @Test
    void selectsActivatedChapterOnAddition() {
      addActiveChapter()
      assertTrue(getActivatedChapter().selected)
    }

  @Test
  void doesNotSelectInactiveChapterOnAddition() {
    addInactiveChapter()
    assertFalse(getInactiveChapter().selected)
  }

    private def addActiveChapter() {
      controller.params.id = "1"
      controller.addChapter()
    }

  private def addInactiveChapter() {
    controller.params.id = "2"
    controller.addChapter()
  }

  private def getInactiveChapter() {
    controller.session.chapters[1]
  }

  private def getActivatedChapter() {
      controller.session.chapters[0]
    }
}