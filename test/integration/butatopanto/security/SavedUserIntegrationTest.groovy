package butatopanto.security;


import grails.test.GrailsUnitTestCase

class SavedUserIntegrationTest extends GrailsUnitTestCase {

  User user

  protected void setUp() {
    user = new User(username: "test", password: "test").save()
  }

  protected void tearDown() {
    if (User.exists(user.id)) {
      User.get(user.id).delete()
    }
  }

  def testExists() {
    assertTrue User.exists(user.id)
  }

  def testDoesNoLongerExistsAfterDelete() {
    user.delete()
    assertFalse User.exists(user.id)
  }
}