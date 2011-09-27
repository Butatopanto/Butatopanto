package butatopanto.sharedtest

import functionaltestplugin.FunctionalTestCase
import org.apache.commons.logging.LogFactory

class UserSensitiveFunctionalTestCase extends FunctionalTestCase {

  static {
    LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
  }

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
