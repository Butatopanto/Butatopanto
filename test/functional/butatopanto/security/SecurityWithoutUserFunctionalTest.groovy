package butatopanto.security

import butatopanto.sharedtest.UserSensitiveFunctionalTestCase

class SecurityWithoutUserFunctionalTest extends UserSensitiveFunctionalTestCase {

  void testShowsLogInPageForUserAdministration() {
    get("/user")
    assertTitle "Login"
  }

  void testShowsLogInPageForRoleAdministration() {
    get("/role")
    assertTitle "Login"
  }

  void testShowsLogInPageForSecurityInfoAdministration() {
    get("/securityInfo")
    assertTitle "Login"
  }

  void testShowsLogInPageForRegistrationCodeAdministration() {
    get("/registrationCode")
    assertTitle "Login"
  }
}
