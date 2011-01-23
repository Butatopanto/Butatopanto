package butatopanto.kanji

import butatopanto.security.User
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class ReviewControllerDirectAccessTest extends GrailsJUnit4ControllerTestCase {

  def springSecurityService
  def masteryService

  ReviewControllerDirectAccessTest() {
    super(ReviewController)
  }

  @Before
  void createUserAndLogIn() {
    new User(username: "Test", password: "Toast").save(failOnError: true)
    springSecurityService.reauthenticate("Test", "Toast")
    masteryService.activateChapter 1
  }

  @Test
  void selectsChapterForReviewWithoutAccessToAssemble() {
    controller.params.id = 1
    controller.addChapter()
    assertTrue(controller.session.chapters[0].selected)
  }

  @Test
  void startsReviewWithKanjiFromGivenRange() {
    controller.params.from = 4
    controller.params.to = 7
    controller.startRange()
    assertTrue(controller.session.review.remainingIds.containsAll(4..7))
  }

   @Test
  void beginsPracticeWhenStartingRangeReview() {
    controller.params.from = 1
    controller.params.to = 2
    controller.startRange()
    assertEquals ([action: 'practice'], controller.redirectArgs)
  }
}