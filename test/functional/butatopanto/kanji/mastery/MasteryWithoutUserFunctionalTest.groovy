package butatopanto.kanji.mastery

import butatopanto.sharedtest.UserSensitiveFunctionalTestCase

class MasteryWithoutUserFunctionalTest extends UserSensitiveFunctionalTestCase {

  void testShowsLogInPageOnListByChapter() {
    get("/mastery/listByChapter")
    assertTitle "Login"
  }

  void testShowsAssemblePageAfterLogIn() {
    get("/mastery/listByChapter/1")
    logInDefaultUser()
    assertTitle "Chapter 1"
  }
}
