package butatopanto.security

import butatopanto.sharedtest.UserSensitiveFunctionalTestCase

class SecurityWithAdminFunctionalTest extends UserSensitiveFunctionalTestCase {

  void setUp() {
    super.setUp()
    logInAdministrator()
  }

  void testAllowsUserAdministration() {
    get("/user")
    assertTitle "User Search"
  }

  void testAllowsRoleAdministration() {
    get("/role")
    assertTitle "Role Search"
  }

  void testAllowsSecurityInfoAdministration() {
    get("/securityInfo/config")
    assertTitle "Security Configuration"
  }

  void testAllowsRegistrationCodeAdministration() {
    get("/registrationCode")
    assertTitle "Registration Code Search"
  }
}