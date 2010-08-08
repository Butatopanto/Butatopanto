package butatopanto.kanji

import grails.test.*

class HeisigTrainingControllerTests extends ControllerUnitTestCase {
   void testReturnsRandomKanjiForShownQuestion() {
    def randomKanji = new Kanji()
    controller.kanjiService = [getRandomKanji: {randomKanji}]
    def kanji = controller.show()["kanji"]
    assertSame randomKanji, kanji
  }

  void testRedirectsIndexToShow(){
    controller.index()
    assertEquals 'show', controller.redirectArgs.action
  }
}
