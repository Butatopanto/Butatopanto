package butatopanto.kanji

import functionaltestplugin.FunctionalTestCase

class UserSensitiveFunctionalTestCase extends FunctionalTestCase {

  void logInDefaultUser() {
    logIn("Sandra", "password")
  }
 
  void logIn(String userName, String password) {
    post('/j_spring_security_check') {
      j_username = userName
      j_password = password
    }
  }
}
