package butatopanto.kanji

import butatopanto.sharedtest.UserSensitiveFunctionalTestCase

class ReviewRangeFunctionalTest extends UserSensitiveFunctionalTestCase {

  void setUp() {
    super.setUp()
    logInDefaultUser()
    goToAssemblePage()
    form() {
      from = 71
      to = 76
      click "practice"
    }
  }

  void testHasReviewWith6Kanji() {
    assertContentContains "6 of 6"
  }

  void testStartsReview() {
    assertTitle "Do you know this Kanji?"
  }

  private void goToAssemblePage() {
    get("/review/assemble/")
  }
}
