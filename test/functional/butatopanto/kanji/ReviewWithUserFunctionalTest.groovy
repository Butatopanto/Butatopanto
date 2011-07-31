package butatopanto.kanji

import butatopanto.sharedtest.UserSensitiveFunctionalTestCase

class ReviewWithUserFunctionalTest extends UserSensitiveFunctionalTestCase {

  public static def controllerUrl = "/assembleReview"

  void setUp() {
    super.setUp()
    logInDefaultUser()
    goToAssembleReviewPage()
  }

  void tearDown() {
    MasteryOfFrame.list().each { it.delete(flush: true)}
  }

  void testShowsAssemblePageOnAssemble() {
    assertTitle "Study which chapters?"
  }

  void testStartsReviewForChapter6With10Frames() {
    startChapter6()
    assertTitle 'Do you know this Kanji?'
    assertContentContains '10 of 10'
  }

  void testShowsAffirmativePracticedKanjiAsRight() {
    startChapter6()
    revealAndConfirmKanji()
    String chartUrl = byId('progress-chart').getSrcAttribute()
    assertTrue(chartUrl, chartUrl.contains('1 right'))
    assertTrue(chartUrl, chartUrl.contains('0 wrong'))
    assertTrue(chartUrl, chartUrl.contains('9 of 10'))
  }

  void testShowsNegativePracticedKanjiAsWrong() {
    startChapter6()
    clickAndWait 'kanji-card'
    clickAndWait 'declineButton'
    String chartUrl = byId('progress-chart').getSrcAttribute()
    assertTrue(chartUrl, chartUrl.contains('0 right'))
    assertTrue(chartUrl, chartUrl.contains('1 wrong'))
    assertTrue(chartUrl, chartUrl.contains('9 of 10'))
  }

  void testShowsStoryDialogForActiveReview() {
    startChapter6()
    assertNotNull byId('showStory')
    revealAndConfirmKanji()
    assertNotNull byId('showStory')
  }

  void testCalculatesCorrectNumberOfDueKanjiAfterCompletingChapter() {
    startChapter6();
    revealAndDeclineKanji()
    for (i in 2..10) {
      revealAndConfirmKanji()
    }
    returnToAssemblePage()
    assertContentContains '1 due'
  }

  void testDoesNotShowStoryDialogOnFinalPage() {
    startChapter6()
    for (i in 1..10) {
      revealAndConfirmKanji()
    }
    assertNull byId('showStory')
  }

  void testRepeatsWrongAfterFinalPage() {
    startChapter6();
    revealAndDeclineKanji()
    for (i in 2..10) {
      revealAndConfirmKanji()
    }
    form() {
      click "repeatWrong"
    }
    assertContentContains '1 of 1'
  }

  void testDoesNotShowOptionToRepeatWrongKanjiIfThereAreNone() {
    startChapter6();
    for (i in 1..10) {
      revealAndConfirmKanji()
    }
    assertContentDoesNotContain 'Repeat wrong Kanji'
  }

  void testRepeatsAllAfterFinalPage() {
    startChapter6();
    for (i in 1..10) {
      revealAndConfirmKanji()
    }
    form('repeatAllForm') {
      click "repeatAll"
    }
    assertContentContains '10 of 10'
  }

  void testStartsNewReviewAfterFinalPage() {
    startChapter6();
    for (i in 1..10) {
      revealAndConfirmKanji()
    }
    form('startNewForm') {
      click "startNew"
    }
    assertTitle "Study which chapters?"
  }

  private def revealAndConfirmKanji() {
    clickAndWait 'kanji-card'
    clickAndWait 'confirmButton'
  }

  private def revealAndDeclineKanji() {
    clickAndWait 'kanji-card'
    clickAndWait 'declineButton'
  }

  private def returnToAssemblePage() {
    click 'Study Kanji'
  }

  protected void clickAndWait(String identifier) {
    click identifier
    getClient().waitForBackgroundJavaScript(10000)
  }

  private void goToAssembleReviewPage() {
    get(controllerUrl + "/assemble")
  }

  private def startChapter6() {
    activateChapter6()
    get(controllerUrl + '/removeChapter/6')
    click 'chapter6'
    click 'Selected'
  }

  private def activateChapter6() {
    get('/mastery/listByChapter/')
    form() {
      from = 95
      to = 104
      click "activate"
    }
  }
}