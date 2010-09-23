package butatopanto.kanji

import butatopanto.security.User
import grails.test.GrailsUnitTestCase

class StoryIntegrationTests extends GrailsUnitTestCase {

  def user = User.get(1)

  protected void setUp() {
    super.setUp()
    def existingStory = new Story(text: 'Hallo', kanji: 'X', user: user)
    existingStory.save();
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testIsInvalidIfEmpty() {
    def story = new Story()
    assertFalse story.validate()
  }


  void testIsInvalidWithoutKanji() {
    def story = new Story(user: new User())
    assertFalse story.validate()
  }

  void testIsValidWithEverythingSet() {
    def newUser = new User(username: "A", password: "B")
    newUser.save()
    def story = new Story(text: 'Hello', kanji: 'Y', user: newUser)
    assertTrue story.validate()
  }

  void testIsInvalidWithoutUser() {
    def story = new Story(kanji: 'Y')
    assertFalse story.validate()
  }

  void testIsInvalidWithDuplicateCombinationOfUserAndKanji() {
    def story = new Story(kanji: 'X', user: user)
    assertFalse story.validate()
  }

  void testIsValidWithDuplicateUserButUniqueKanji() {
    def story = new Story(kanji: 'Y', user: user)
    assertTrue story.validate()
  }

  void testIsValidWithDuplicateKanjiButUniqueUser() {
    def newUser = new User(username: "A", password: "B")
    newUser.save()
    def story = new Story(text: 'Hello', kanji: 'X', user: newUser)
    assertTrue story.validate()
  }
}