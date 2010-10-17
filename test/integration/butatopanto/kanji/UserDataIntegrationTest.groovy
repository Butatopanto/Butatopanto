package butatopanto.kanji

import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class UserDataIntegrationTest extends GrailsJUnit4TestCase {
  HeisigUser heisigUser

  @Before
  public void saveFrameReview() {
    heisigUser = new HeisigUser(userName: "Urs")
    heisigUser.save()
  }

  @Test
  void doesExist() {
    assertTrue HeisigUser.exists(heisigUser.id)
  }

  @Test
  void doesNoLongerExistAfterDelete() {
    heisigUser.delete()
    assertFalse heisigUser.exists(heisigUser.id)
  }
}