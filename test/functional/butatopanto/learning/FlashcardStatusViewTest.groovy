package butatopanto.learning

import butatopanto.kanji.MasteryOfFrame
import butatopanto.kanji.ReviewWithUserFunctionalTest
import butatopanto.sharedtest.UserSensitiveFunctionalTestCase
import org.junit.Test

class FlashcardStatusViewTest extends UserSensitiveFunctionalTestCase {

  void setUp() {
    super.setUp()
    logInDefaultUser()
    activateChapter6()
    goToAssembleReviewPage()
    startChapter6()
    goToFlashCardStatusPage()
  }

  void tearDown() {
    MasteryOfFrame.list().each {it.delete(flush: true)}
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

  @Test
  void startsReviewWithClickOnBox1() {
    click 'box1'
    assertTitle 'Do you know this Kanji?'
  }

  private void goToAssembleReviewPage() {
    get(ReviewWithUserFunctionalTest.controllerUrl + "/assemble")
  }

  private void goToFlashCardStatusPage() {
    get("/flashcard")
  }

  private def startChapter6() {
    get(ReviewWithUserFunctionalTest.controllerUrl + '/removeChapter/6 ')
    click 'chapter6'
  }

  //Duplicate In butatopanto.kanji.ReviewWithUserFunctionalTest
  private def activateChapter6() {
    get('/mastery/listByChapter/')
    form() {
      from = 95
      to = 104
      click "activate"
    }
  }
}