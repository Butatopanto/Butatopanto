package butatopanto.learning

import butatopanto.kanji.FlashcardController
import butatopanto.kanji.MasteryOfFrame
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class FlashcardController_NoKanjiLearnedTest extends GrailsJUnit4ControllerTestCase {

  FlashcardController_NoKanjiLearnedTest() {
    super(FlashcardController)
  }

  @Before
  void prepareMasteryOfFrame() {
    mockDomain(MasteryOfFrame.class)
  }


  @Before
  void prepareMasteryQueryService() {
    controller.masteryQueryService = [listMasteriesForBox: {[]}]
  }


  @Test
  void redirectsIndexToStatus() {
    controller.index()
    assertEquals([action: "status"], controller.redirectArgs)
  }

  @Test
  void hasEightBoxes() {
    assertEquals(8, getBoxes().size())
  }

  @Test
  void hasOneBoxForEachOneKnownToLeitnerService() {
    assertEquals([1, 2, 3, 4, 5, 6, 7, 8], getBoxes()*.number)
  }

  @Test
  void eachBoxIsEmpty() {
    assertFalse getBoxes().findAll() {it.totalKanji > 0} as boolean
  }

  @Test
  void noBoxHasKanjiDue() {
    assertFalse getBoxes().findAll() {it.dueKanji > 0} as boolean
  }

  private def getBoxes() {
    controller.status().boxes
  }
}
