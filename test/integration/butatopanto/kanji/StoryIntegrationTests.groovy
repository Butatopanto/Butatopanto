package butatopanto.kanji

import butatopanto.sharedtest.GrailsJUnit4TestCase
import butatopanto.sharedtest.ValidationUtilities
import org.junit.Before
import org.junit.Test

class StoryIntegrationTests extends GrailsJUnit4TestCase {

  UserData userData

  @Before
  public void saveStory() {
    userData = new UserData(userName: "the user")
    def story = new Story(text: 'Hallo', frame: Frame.get(1), userData: userData)
    userData.addToStoryList(story).save(failOnError: true)
  }

  @Test
  void addedStoryExists() {
    assertEquals 1, Story.list().size()
  }

  @Test
  void userDataMustNotBeNull() {
    assertNotNull ValidationUtilities.getValidationFieldError(new Story(), "userData")
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
    userData.addToStoryList(story).save(failOnError: true)
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

  private UserData findUserData() {
    def id = userData.id
    return UserData.get(id)
  }
}