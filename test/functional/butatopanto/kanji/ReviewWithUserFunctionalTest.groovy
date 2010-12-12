package butatopanto.kanji

class ReviewWithUserFunctionalTest extends UserSensitiveFunctionalTestCase {

  void setUp() {
    super.setUp()
    logInDefaultUser()
    goToAssembleReviewPage()
  }

  void testShowsAssemblePageOnAssemble() {
    assertTitle "Review which chapters?"
  }

  void testStartsReviewForChapter6With10Frames() {
    startChapter6()
    assertTitle 'Do you know this Kanji?'
    assertContentContains '10 of 10'
  }

  void testShowsAffirmativePracticedKanjiAsRight() {
    startChapter6()
    clickAndWaitLong 'kanji-card'
    clickAndWaitLong 'confirmButton'
    String chartUrl = byId('progress-chart').getSrcAttribute()
    assertTrue(chartUrl, chartUrl.contains('1 right'))
    assertTrue(chartUrl, chartUrl.contains('0 wrong'))
    assertTrue(chartUrl, chartUrl.contains('9 of 10'))
  }

  void testShowsNegativePracticedKanjiAsWrong() {
    startChapter6()
    clickAndWaitShort 'kanji-card'
    clickAndWaitLong 'declineButton'
    String chartUrl = byId('progress-chart').getSrcAttribute()
    assertTrue(chartUrl, chartUrl.contains('0 right'))
    assertTrue(chartUrl, chartUrl.contains('1 wrong'))
    assertTrue(chartUrl, chartUrl.contains('9 of 10'))
  }

  void pendingTestDoesNotShowStoryDialogOnFinalPage() {
    startChapter6()
    assertNotNull byId('showStory')
    for (i in 1..10) {
      clickAndWaitShort 'kanji-card'
      clickAndWaitLong 'confirmButton'
    }
    assertNull byId('showStory')
  }

  protected void clickAndWaitShort(String identifier) {
    clickAndWait(identifier, 500)
  }

  protected void clickAndWaitLong(String identifier) {
    clickAndWait(identifier, 2000)
  }

  protected void clickAndWait(String identifier, long milliseconds) {
    click identifier
    Thread.sleep(milliseconds)
  }

  private void goToAssembleReviewPage() {
    get("/review/assemble")
  }

  private def startChapter6() {
    get('/review/removeChapter/6')
    click 'chapter6'
    click 'Selected'
  }
}
