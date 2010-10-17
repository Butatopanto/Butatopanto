package butatopanto.kanji

import butatopanto.sharedtest.GrailsJUnit4TestCase
import butatopanto.sharedtest.ValidationUtilities
import org.junit.Before
import org.junit.Test

class StoryIntegrationTests extends GrailsJUnit4TestCase {

  HeisigUser heisigUser

  @Before
  public void saveStory() {
    heisigUser = new HeisigUser(userName: "the user")
    def story = new Story(text: 'Hallo', frame: Frame.get(1), userData: heisigUser)
    heisigUser.addToStoryList(story).save(failOnError: true)
  }

  @Test
  void addedStoryExists() {
    assertTrue Story.exists((heisigUser.storyList as List)[0].id)
  }

  @Test
  void userDataMustNotBeNull() {
    assertNotNull ValidationUtilities.getValidationFieldError(new Story(), "user")
  }

  @Test
  void frameMustNotBeNull() {
    assertNotNull ValidationUtilities.getValidationFieldError(new Story(), "frame")
  }

  @Test
  void textMayBeNull() {
    assertNull ValidationUtilities.getValidationFieldError(new Story(), "text")
  }

  @Test
  void textMayBeBlank() {
    assertNull ValidationUtilities.getValidationFieldError(new Story(text: ''), "text")
  }

  @Test(expected = grails.validation.ValidationException)
  void doesNotAllowAddingASecondStoryForSameFrame() {
    def story = new Story(frame: Frame.get(1))
    heisigUser.addToStoryList(story).save(failOnError: true)
  }

  @Test
  void cascadesDeleteFromUserData() {
    Story story = findFirstStoryFromUserData()
    findUserData().delete()
    assertFalse Story.exists(story.id)
  }

  private Story findFirstStoryFromUserData() {
    (findUserData().storyList as List)[0]
  }

  private HeisigUser findUserData() {
    def id = heisigUser.id
    return HeisigUser.get(id)
  }
}