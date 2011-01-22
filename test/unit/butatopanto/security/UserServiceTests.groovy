package butatopanto.security

import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test

class UserServiceTests extends GrailsJUnit4TestCase {

  UserService userService = new UserService()

  @Test
  void hasNoCurrentUserIfNoneAuthenticated() {
    userService.springSecurityService = [authentication: null]
    def user = userService.getCurrentUser()
    assertNull user
  }
}
