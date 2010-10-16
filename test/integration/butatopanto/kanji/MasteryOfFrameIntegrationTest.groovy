package butatopanto.kanji

import butatopanto.sharedtest.GrailsJUnit4TestCase
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
  void addedMasteryExists() {
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
    MasteryOfFrame mastery = findFirstMasteryFromUserData()
    findUserData().delete()
    assertFalse MasteryOfFrame.exists(mastery.id)
  }

  @Test(expected=grails.validation.ValidationException)
  void doesNotAllowAddingASecondMasteryForSameFrame() {
    def mastery = new MasteryOfFrame(frame: Frame.get(1))
    userData.addToMasteryList(mastery).save(failOnError: true)
  }

  private MasteryOfFrame deleteMastery() {
    MasteryOfFrame mastery = findFirstMasteryFromUserData()
    findUserData().removeFromMasteryList(mastery)
    mastery.delete()
    return mastery
  }

  private MasteryOfFrame findFirstMasteryFromUserData() {
    (findUserData().masteryList as List)[0]
  }

  private UserData findUserData() {
    def id = userData.id
    return UserData.get(id)
  }
}