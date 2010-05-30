package butatopanto.vocable

import grails.test.ControllerUnitTestCase

class QuestionControllerTests extends ControllerUnitTestCase {

  def controller

  protected void setUp() {
    super.setUp()
    controller = new QuestionController()
  }

  void testReturnsRandomVocableForShownQuestion() {
    def randomVocable = new Vocable();
    controller.vocableService = [getRandomVocable: {randomVocable}]
    def vocable = controller.show()["vocable"]
    assertSame vocable, randomVocable
  }

  protected void tearDown() {
    super.tearDown()
  }
}