package butatopanto.learning

import butatopanto.kanji.MasteryOfFrame
import butatopanto.kanji.ReviewWithUserFunctionalTest
import butatopanto.sharedtest.UserSensitiveFunctionalTestCase
import org.junit.Test

class FlashcardStatusView_ErrorTest extends UserSensitiveFunctionalTestCase {

  void setUp() {
    super.setUp()
    logInDefaultUser()
    goToAssembleReviewPage()
    selectAnyChapter()
    tryToLearnMasteredKanjiViaFlashCards()
  }

  void tearDown() {
    MasteryOfFrame.list().each {it.delete(flush: true)}
  }

  @Test
  void showsMessageAboutUselessnessOfLearningMasteredKanji() {
    assertContentContains "You will hamper your progress by reviewing Kanji that are not yet due."
  }

  private void goToAssembleReviewPage() {
    get(ReviewWithUserFunctionalTest.controllerUrl + "/assemble")
  }

  private void tryToLearnMasteredKanjiViaFlashCards() {
    get("/flashcard/startMastered")
  }

  private def selectAnyChapter() {
    get(ReviewWithUserFunctionalTest.controllerUrl + '/removeChapter/6 ')
    click 'chapter6'
  }
}