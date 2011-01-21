package butatopanto.learning

import butatopanto.sharedtest.UserSensitiveFunctionalTestCase
import org.junit.Test

class FlashcardStatusViewTest extends UserSensitiveFunctionalTestCase {

  void setUp() {
    super.setUp()
    logInDefaultUser()
    goToAssembleReviewPage()
    selectAnyChapter()
    goToFlashCardStatusPage()
  }

  @Test
  void hasTitleFlashcardStatus() {
    assertTitle "Flashcard Status"
  }

  @Test
  void hasHeadingAboutCurrentStatus() {
    assertContentContains "Your current status"
  }

  @Test
   void hasSomeKanjiInBox1() {
    assertContentContains "Box 1: 10 due out of 10"
  }

  private void goToAssembleReviewPage() {
    get("/review/assemble")
  }

  private void goToFlashCardStatusPage() {
    get("/flashcard")
  }

  private def selectAnyChapter() {
    get('/review/removeChapter/6')
    click 'chapter6'
  }
}