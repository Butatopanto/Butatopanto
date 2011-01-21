package butatopanto.learning

import butatopanto.kanji.FlashcardController
import butatopanto.kanji.MasteryOfFrame
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class FlashcardController_KanjiLearnedTest extends GrailsJUnit4ControllerTestCase {

  FlashcardController_KanjiLearnedTest() {
    super(FlashcardController)
  }

  @Before
  void prepareMasteryOfFrame() {
    def dueMastery = new MasteryOfFrame(box: 2)
    def nonDueMastery = new MasteryOfFrame(box: 2)
    mockDomain(MasteryOfFrame.class, [nonDueMastery, dueMastery])
    controller.leitnerService = [isDue: {it == dueMastery}]
  }

  @Test
  void hasTotalKanjiEqualToMasteriesInBox() {
    assertEquals([0, 2, 0, 0, 0, 0, 0, 0], getBoxes()*.totalKanji)
  }

   @Test
  void hasDueKanjiAccordingToLeitnerService() {
    assertEquals([0, 1, 0, 0, 0, 0, 0, 0], getBoxes()*.dueKanji)
  }

  private def getBoxes() {
    controller.status().boxes
  }
}
