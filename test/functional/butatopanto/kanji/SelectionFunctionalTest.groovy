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

  void testHasSelectableKanjiIfAssemblyPageIsRevisitedAfterCardsWereAdded() {
    goToAssembleReviewPage()
    activateChapter6()
    goToAssembleReviewPage()
    click 'chapter6'
    assertContentContainsStrict 'Selected'
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