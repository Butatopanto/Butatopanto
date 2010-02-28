package net.butatopanto.vocable

import grails.test.ControllerUnitTestCase

class QuestionControllerTests extends ControllerUnitTestCase {

   def controller

  protected void setUp() {
    super.setUp()
    controller = new QuestionController()
  }

  void testCreatesQuestionFromRandomVocable() {
    controller.vocableService = [getRandomVocable:{new Vocable(meaning: "Has�")}]
    def question = controller.show()["questionInstance"]
    assertEquals "Has�", question.vocable.meaning 
  }

  protected void tearDown() {
    super.tearDown()
  }
}