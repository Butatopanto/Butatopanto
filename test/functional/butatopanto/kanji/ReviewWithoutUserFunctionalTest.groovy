package butatopanto.kanji

import functionaltestplugin.FunctionalTestCase

class ReviewWithoutUserFunctionalTest extends UserSensitiveFunctionalTestCase {

  void testShowsLogInPageOnAssemble() {
    get("/review/assemble")
    assertTitle "Login"
  }

  void testShowsAssemblePageAfterLogIn() {
    get("/review/assemble")
    logIn("Sandra", "password")
    assertTitle "Review which chapters?"
  }
}
