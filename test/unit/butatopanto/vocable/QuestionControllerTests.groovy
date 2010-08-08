package butatopanto.vocable

import grails.test.ControllerUnitTestCase

class QuestionControllerTests extends ControllerUnitTestCase {

  void testReturnsRandomVocableForShownQuestion() {
    def randomVocable = new Vocable()
    controller.vocableService = [getRandomVocable: {randomVocable}]
    def vocable = controller.show()["vocable"]
    assertSame vocable, randomVocable
  }

  void testReturnsRandomVocableForShownQuestionWithList() {
    def listVocable = new Vocable(meaning:'meaning', kana:"kana")
    mockDomain Vocable, [listVocable, new Vocable(meaning:"inListe", kana:"listenKana")]
    mockDomain Studylist, [new Studylist(name: 'Testliste', vocables:[listVocable])]
    controller.params.id = 'Testliste'
    controller.vocableService = [getRandomVocable: {list -> list.vocables.iterator().next() }]
    def vocable = controller.show()["vocable"]
    assertSame vocable, listVocable
  }
}