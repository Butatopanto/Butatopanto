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
    def differentUserMastery = new MasteryOfFrame(box: 2)
    def dueMastery = new MasteryOfFrame(box: 2)
    def nonDueMastery = new MasteryOfFrame(box: 2)
    controller.masteryQueryService = [listMasteriesForBox: {it == 2 ? [dueMastery, nonDueMastery] : []}]
    mockDomain(MasteryOfFrame.class, [nonDueMastery, dueMastery, differentUserMastery])
    controller.leitnerService = [isDue: {it == dueMastery}, getExpirationIntervalForBox: {it}]
  }

  @Test
  void hasMasteredKanjiEqualToNonDueKanji() {
    assertEquals([0, 1, 0, 0, 0, 0, 0, 0], getBoxes()*.masteredKanji)
  }

  @Test
  void hasDueKanjiAccordingToLeitnerService() {
    assertEquals([0, 1, 0, 0, 0, 0, 0, 0], getBoxes()*.dueKanji)
  }

  private def getBoxes() {
    controller.status().boxes
  }
}
