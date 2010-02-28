package net.butatopanto.vocable

import grails.test.ControllerUnitTestCase

class QuestionControllerTests extends ControllerUnitTestCase {

   def controller

  protected void setUp() {
    super.setUp()
    controller = new QuestionController()
  }

  void testCreatesQuestionFromRandomVocable() {
    controller.vocableService = [getRandomVocable:{new Vocable(meaning: "Hasä")}]
    def vocable = controller.show()["vocable"]
    assertEquals "Hasä", vocable.meaning 
  }

  protected void tearDown() {
    super.tearDown()
  }
}