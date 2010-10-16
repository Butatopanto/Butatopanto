package butatopanto.kanji

import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class UserDataIntegrationTest extends GrailsJUnit4TestCase {
  UserData userData

  @Before
  public void saveFrameReview() {
    userData = new UserData(userName: "Sandra")
    userData.save()
  }

  @Test
  void doesExist() {
    assertTrue UserData.exists(userData.id)
  }

  @Test
  void doesNoLongerExistAfterDelete() {
    userData.delete()
    assertFalse UserData.exists(userData.id)
  }
}