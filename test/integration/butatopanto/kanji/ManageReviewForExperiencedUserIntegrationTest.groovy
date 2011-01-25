package butatopanto.kanji;


import butatopanto.security.User
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
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
    masteryService.activateChapter(1)
    controller.assemble()
  }

  @Before
  void addMetaDataToTagLibs() {
    registerMetaClass ProgressTagLib
    registerMetaClass HeisigTagLib
    ProgressTagLib.metaClass.g = [message: { Map map -> return "error message" }, remoteFunction: {}]
    HeisigTagLib.metaClass.g = [message: { Map map -> return "error message" }, remoteFunction: {}]
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
  void doesNotMarkActiveChapterAsSelected() {
    assertFalse controller.session.chapters[0].selected
  }

  @Test
  void marksActiveChapter() {
    assertTrue(controller.session.chapters[0].active)
  }

  @Test
  void reducesDueCountForNextAssemblyAfterCorrectReview() {
    controller.params.id = "1"
    controller.addChapter()
    controller.startSelectedChapters()
    controller.params.reviewCorrect = "true"
    controller.ajaxResolve()
    controller.assemble()
    assertEquals(14, controller.session.chapters[0].dueFrameCount)
  }
}