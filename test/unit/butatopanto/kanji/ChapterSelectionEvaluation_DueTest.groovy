package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test

class ChapterSelectionEvaluation_DueTest extends GrailsJUnit4TestCase {

  @Test
  void findsDueKanjiIfAny() {
    def evaluation = evaluateWithDueCount(1)
    assertTrue(evaluation.hasDue())
  }

  @Test
  void findsNoDueKanjiIfNone() {
    def evaluation = evaluateWithDueCount(0)
    assertFalse(evaluation.hasDue())
  }

  @Test
  void findsSelectedKanjiIfAny() {
    def evaluation = evaluateWithSelected(true)
    assertTrue(evaluation.hasSelectedChapter())
  }

  @Test
  void findsNoSelectedKanjiIfNone() {
    def evaluation = evaluateWithSelected(false)
    assertFalse(evaluation.hasSelectedChapter())
  }

  @Test
  void findsSelectedAndDueKanjiIfAny() {
    def evaluation = evaluateWithSelectedAndDue(1)
    assertTrue(evaluation.hasDueSelected())
  }

  @Test
  void findsNoSelectedAndDueKanjiIfNoneDue() {
    def evaluation = evaluateWithSelectedAndDue(0)
    assertFalse(evaluation.hasDueSelected())
  }

  private def evaluateWithSelectedAndDue(int due) {
    def selection = new ChapterSelection(selected: true, dueFrameCount: due)
    evaluate(selection)
  }

  private def evaluateWithSelected(boolean selected) {
    def selection = new ChapterSelection(selected: selected)
    evaluate(selection)
  }

  private def evaluateWithDueCount(int due) {
    def selection = new ChapterSelection(dueFrameCount: due)
    evaluate(selection)
  }

  private def evaluate(ChapterSelection selection) {
    def chapters = [
            selection
    ]
    new ChapterSelectionEvaluation(chapters: chapters)
  }
}