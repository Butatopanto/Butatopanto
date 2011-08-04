package butatopanto.kanji

import butatopanto.sharedtest.UserSensitiveFunctionalTestCase

class SelectionFunctionalTest extends UserSensitiveFunctionalTestCase {

  public static def controllerUrl = "/assembleReview"

  void setUp() {
    super.setUp()
    logInDefaultUser()
  }

  void tearDown() {
    MasteryOfFrame.list().each { it.delete(flush: true)}
  }

  void testStartsReviewForChapter6With10Frames() {
      goToAssembleReviewPage()
      activateChapter6()
      goToAssembleReviewPage()
      get(controllerUrl + '/removeChapter/6')
      click 'chapter6'
      assertContentContainsStrict 'Selected'
  }

  private def revealAndConfirmKanji() {
    clickAndWait 'kanji-card'
    clickAndWait 'confirmButton'
  }

  private def revealAndDeclineKanji() {
    clickAndWait 'kanji-card'
    clickAndWait 'declineButton'
  }

  protected void clickAndWait(String identifier) {
    click identifier
    getClient().waitForBackgroundJavaScript(10000)
  }

  private void goToAssembleReviewPage() {
    get(controllerUrl + "/assemble")
  }

  //Duplicate In FlashCardStatusViewTest
  private def activateChapter6() {
    get('/mastery/listByChapter/')
    form() {
      from = 95
      to = 104
      click "activate"
    }
  }
}