package butatopanto.kanji

import butatopanto.test.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class MasteryOfFrameIntegrationTest extends GrailsJUnit4TestCase {
  UserData userData

  @Before
  public void saveMastery() {
    userData = new UserData(userName: "the user")
    def mastery = new MasteryOfFrame(frame: Frame.get(1))
    userData.addToMasteryList(mastery).save(failOnError: true)
  }

  @Test
  void userDataHasOneMastery() {
    assertEquals 1, MasteryOfFrame.list().size()
  }

  @Test
  void doesNoLongerExistAfterRemoval() {
    MasteryOfFrame mastery = deleteMastery()
    assertFalse MasteryOfFrame.exists(mastery.id)
  }

  @Test
  void noLongerBelongsToUserDataAfterRemoval() {
    deleteMastery()
    assertEquals 0, findUserData().masteryList.size()
  }

  @Test
  void cascadesDeleteFromUserData() {
    MasteryOfFrame mastery = findFirstFrameReviewFromUserData()
    findUserData().delete()
    assertFalse MasteryOfFrame.exists(mastery.id)
  }

  private MasteryOfFrame deleteMastery() {
    MasteryOfFrame mastery = findFirstFrameReviewFromUserData()
    findUserData().removeFromMasteryList(mastery)
    mastery.delete()
    return mastery
  }

  private MasteryOfFrame findFirstFrameReviewFromUserData() {
    def reviewList = findUserData().masteryList as List
    reviewList[0]
  }

  private UserData findUserData() {
    def id = userData.id
    return UserData.get(id)
  }
}