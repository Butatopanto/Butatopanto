package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test

class ChapterSelectionEvaluationTest extends GrailsJUnit4TestCase {

  def chapters = [
          new ChapterSelection(selected: true, active: true, chapterNumber: 1),
          new ChapterSelection(selected: false, active: true, chapterNumber: 2),
          new ChapterSelection(selected: true, active: false, chapterNumber: 3),
  ]

  def evaluation = new ChapterSelectionEvaluation(chapters: chapters)

  @Test
  void filtersSelectedChapterNumbers() {
    assertEquals([1, 3], evaluation.getSelectedChapterNumbers())
  }

  @Test
  void findsChapterById() {
    assertEquals(chapters[1], evaluation.getChapterForNumber(2))
  }
}