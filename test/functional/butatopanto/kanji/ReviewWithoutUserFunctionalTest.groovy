package butatopanto.kanji

import butatopanto.sharedtest.UserSensitiveFunctionalTestCase

class ReviewWithoutUserFunctionalTest extends UserSensitiveFunctionalTestCase {

  void testShowsLogInPageOnAssemble() {
    get("/review/assemble")
    assertTitle "Login"
  }

  void testShowsAssemblePageAfterLogIn() {
    get("/review/assemble")
    logInDefaultUser()
    assertTitle "Study which chapters?"
  }
}
