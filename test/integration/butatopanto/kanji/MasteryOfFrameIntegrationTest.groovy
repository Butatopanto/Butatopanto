package butatopanto.kanji

import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class MasteryOfFrameIntegrationTest extends GrailsJUnit4TestCase {
  HeisigUser heisigUser

  @Before
  public void saveMastery() {
    heisigUser = new HeisigUser(userName: "the user")
    def mastery = new MasteryOfFrame(frame: Frame.get(1), dueDate: new Date())
    heisigUser.addToMasteryList(mastery).save(failOnError: true)
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
    assertEquals 0, findHeisigUser().masteryList.size()
  }

  @Test
  void cascadesDeleteFromUserData() {
    MasteryOfFrame mastery = findFirstMasteryFromUserData()
    findHeisigUser().delete()
    assertFalse MasteryOfFrame.exists(mastery.id)
  }

  @Test(expected = grails.validation.ValidationException)
  void doesNotAllowAddingASecondMasteryForSameFrame() {
    def mastery = new MasteryOfFrame(frame: Frame.get(1))
    heisigUser.addToMasteryList(mastery).save(failOnError: true)
  }

  private MasteryOfFrame deleteMastery() {
    MasteryOfFrame mastery = findFirstMasteryFromUserData()
    findHeisigUser().removeFromMasteryList(mastery)
    mastery.delete()
    return mastery
  }

  private MasteryOfFrame findFirstMasteryFromUserData() {
    (findHeisigUser().masteryList as List)[0]
  }

  private HeisigUser findHeisigUser() {
    def id = heisigUser.id
    return HeisigUser.get(id)
  }
}