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
  void hasBoxCssClassForActiveFrame() {
    masteredFrame.box = 1
    assertEquals "box1", cssClasses[1]
  }

  @Test
  void visualizesStoriesForInactiveFrames() {
    masteredFrame.hasStory = true
    assertEquals "withStory", cssClasses[2]
  }

  @Test
  void visualizesStoriesForActiveFrames() {
    masteredFrame.hasStory = true
    masteredFrame.box = 1
    assertEquals "withStory", cssClasses[2]
  }

  @Test
  void visualizesLackOfStoriesForActiveFrames() {
    masteredFrame.box = 1
    assertEquals "withoutStory", cssClasses[2]
  }

  @Test
  void doesNotVisualizeLackOfStoriesForInactiveFrames() {
    assertEquals 2, cssClasses.size()
  }

  private def getCssClasses() {
    return masteredFrame.getCssClass().split(" ")
  }
}