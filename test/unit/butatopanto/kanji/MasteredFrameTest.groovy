package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test

class MasteredFrameTest extends GrailsJUnit4TestCase {

  def masteredFrame = new MasteredFrame(box: 0)

  @Test
  void isNotActiveWithoutBox() {
    assertFalse new MasteredFrame(box: null).isActive()
  }

  @Test
  void isNotActiveWithBoxZero() {
    assertFalse masteredFrame.isActive()
  }

  @Test
  void hasKanjiBoxCssClass() {
    def cssClasses = masteredFrame.getCssClass().split(" ")
    assertEquals "kanjibox", cssClasses[0]
  }

  @Test
  void hasInactiveCssClass() {
    def cssClasses = masteredFrame.getCssClass().split(" ")
    assertEquals "inactiveKanji", cssClasses[1]
  }
}