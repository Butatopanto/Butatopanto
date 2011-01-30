package butatopanto.learning

import butatopanto.kanji.FlashcardController
import butatopanto.kanji.Frame
import butatopanto.kanji.MasteryOfFrame
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class FlashcardController_StartTest extends GrailsJUnit4ControllerTestCase {

  private def frame1 = new Frame(number: 1, kanji: 'x', keyword: 'y', chapter: 2)
  private def frame2 = new Frame(number: 2, kanji: 'x', keyword: 'y', chapter: 2)
  private def dueMastery1 = new MasteryOfFrame(box: 2, frame: frame1)
  private def dueMastery2 = new MasteryOfFrame(box: 2, frame: frame2)

  FlashcardController_StartTest() {
    super(FlashcardController)
  }

  @Before
  void prepareMasteryOfFrame() {
    def differentUserMastery = new MasteryOfFrame(box: 2)
    def nonDueMastery = new MasteryOfFrame(box: 2)
    controller.masteryQueryService = [listMasteriesForBox: {int it -> it == 2 ? [dueMastery1, dueMastery2, nonDueMastery] : []}]
    mockDomain(MasteryOfFrame.class, [nonDueMastery, dueMastery1, dueMastery2, differentUserMastery])
    mockDomain(Frame.class, [frame1, frame2])
    controller.leitnerService = [isDue: {it == dueMastery1 || it == dueMastery2}, getExpirationIntervalForBox: {it}]
  }

  @Test
  void showsStatusIfBoxHasNoDueKanji() {
    startBox 1
    assertEquals('status', controller.redirectArgs.action)
  }

  @Test
  void startsReviewWithAllDueInBox() {
    controller.params.id = 2
    controller.startBox()
    assertEquals('assembleReview', controller.redirectArgs.controller)
    assertEquals('startList', controller.redirectArgs.action)
    assertEquals([1, 2], controller.flash.kanji)
  }

  @Test
  void handlesStringIdFromUrlGracefully() {
    controller.params.id = "2"
    controller.startBox()
    assertEquals([1, 2], controller.flash.kanji)
  }

  @Test
  void startsAllDueViaAssembleReview() {
    controller.startDue()
    assertEquals('assembleReview', controller.redirectArgs.controller)
    assertEquals('startDue', controller.redirectArgs.action)
  }

  @Test
  void showsStatusInsteadOfStartingMastered() {
    controller.startMastered()
    assertEquals('status', controller.redirectArgs.action)
  }

    @Test
  void informsAboutUselessnessOfActionWhenStartingMastered() {
    controller.startMastered()
    assertEquals('flashcard.chart.learnNoMastered', controller.flash.message)
  }


  private def startBox(int box) {
    controller.params.id = box
    controller.startBox()
  }
}