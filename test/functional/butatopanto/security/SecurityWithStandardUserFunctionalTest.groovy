package butatopanto.security

import butatopanto.sharedtest.UserSensitiveFunctionalTestCase

class SecurityWithStandardUserFunctionalTest extends UserSensitiveFunctionalTestCase {

  void setUp() {
    super.setUp()
    logInDefaultUser()
  }

  void testDeniesAccessToUserAdministration() {
    get("/user")
    assertTitle "Denied"
  }

  void testDeniesAccessToRoleAdministration() {
    get("/role")
    assertTitle "Denied"
  }

  void testDeniesAccessToSecurityInfoAdministration() {
    get("/securityInfo")
    assertTitle "Denied"
  }

  void testDeniesAccessToRegistrationCodeAdministration() {
    get("/registrationCode")
    assertTitle "Denied"
  }
}