package butatopanto.kanji

import functionaltestplugin.FunctionalTestCase

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
    click 'chapter6'
    click 'Selected'
    assertTitle 'Do you know this Kanji?'
    assertContentContains '10 of 10'
  }

  void pendingTestShowsAffirmativePracticedKanjiAsRight() {
    click 'chapter6'
    click 'Selected'
    click 'kanji-card'
    click 'affirmative'
    assertContentContains '1 right'
    assertContentContains '9 of 10'
  }

  private void goToAssembleReviewPage() {
    get("/review/assemble")
  }
}
