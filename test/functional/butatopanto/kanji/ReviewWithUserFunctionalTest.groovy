package butatopanto.kanji

import functionaltestplugin.FunctionalTestCase

class ReviewWithUserFunctionalTest extends UserSensitiveFunctionalTestCase {

  void setUp() {
    super.setUp()
    logIn("Sandra", "password")
  }

  void testShowsAssemblePageOnAssemble() {
    get("/review/assemble")
    assertTitle "Review which chapters?"
  }
}
