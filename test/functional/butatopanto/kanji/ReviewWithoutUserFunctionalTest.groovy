package butatopanto.kanji

import butatopanto.sharedtest.UserSensitiveFunctionalTestCase

class ReviewWithoutUserFunctionalTest extends UserSensitiveFunctionalTestCase {

  public void setUp() {
    super.setUp()
    get '/assembleReview/assemble'
  }

  void testShowsLogInPageOnAssemble() {
    assertTitle "Login"
  }

  void testShowsAssemblePageAfterLogIn() {
    logInDefaultUser()
    assertTitle "Study which chapters?"
  }
}