package butatopanto.sharedtest

import functionaltestplugin.FunctionalTestCase

class UserSensitiveFunctionalTestCase extends FunctionalTestCase {

  void logInDefaultUser() {
    logIn("Gast", "password")
  }

  void logInAdministrator() {
    logIn("Sandra", "password")
  }
 
  void logIn(String userName, String password) {
    post('/j_spring_security_check') {
      j_username = userName
      j_password = password
    }
  }
}
