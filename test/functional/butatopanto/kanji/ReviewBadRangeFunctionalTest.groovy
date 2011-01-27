package butatopanto.kanji

import butatopanto.sharedtest.UserSensitiveFunctionalTestCase

class ReviewBadRangeFunctionalTest extends UserSensitiveFunctionalTestCase {

  void setUp() {
    super.setUp()
    logInDefaultUser()
    goToAssemblePage()
    form() {
      from = 71
      to = "abc"
      click "practice"
    }
  }

  void testNotifiesAboutBadInput() {
    assertContentContains "Please enter the numbers of the first and last Kanji you want to practice."
  }

  private void goToAssemblePage() {
    get("/review/assemble/")
  }
}
