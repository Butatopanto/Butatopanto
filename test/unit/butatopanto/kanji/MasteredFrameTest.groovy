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
    assertEquals "kanjibox", cssClasses[0]
  }

  @Test
  void hasInactiveCssClass() {
    assertEquals "inactiveKanji", cssClasses[1]
  }

  @Test
  void visualizesStoriesForInactiveFrames() {
    masteredFrame.hasStory = true
    assertEquals "withStory", cssClasses[2]
  }

  @Test
  void doesNotVisualizeLackOfStoriesForInactiveFrames() {
    assertEquals 2, cssClasses.size()
  }

  private def getCssClasses() {
    return masteredFrame.getCssClass().split(" ")
  }
}