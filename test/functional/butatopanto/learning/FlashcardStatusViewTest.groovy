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
    assertContentContains "How many Kanji are in which box of flashcards?"
  }

  @Test
   void hasSomeKanjiInBox1() {
    assertContentContains "I"
    assertContentContains "10 due"
    assertContentContains "0 mast."
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