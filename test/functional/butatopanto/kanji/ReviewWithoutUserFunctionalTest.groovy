package butatopanto.kanji

import functionaltestplugin.FunctionalTestCase

class ReviewWithoutUserFunctionalTest extends UserSensitiveFunctionalTestCase {

  void testShowsLogInPageOnAssemble() {
    get("/review/assemble")
    assertTitle "Login"
  }

  void testShowsAssemblePageAfterLogIn() {
    get("/review/assemble")
    logInDefaultUser()
    assertTitle "Review which chapters?"
  }
}
