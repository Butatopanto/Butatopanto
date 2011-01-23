package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test

class ActiveMasteredFrameTest extends GrailsJUnit4TestCase {

  private MasteredFrame masteredFrame = new MasteredFrame(box: 1)

  @Test
  void isActive() {
    assertTrue masteredFrame.isActive()
  }

  @Test
  void hasKanjiBoxCssClass() {
    def cssClass = masteredFrame.getCssClass().split(" ")
    assertEquals "kanjibox", cssClass[0]
  }

  @Test
  void hasBoxOneCssClass() {
    def cssClass = masteredFrame.getCssClass().split(" ")
    assertEquals "box1", cssClass[1]
  }

  @Test
  void hasWithStoryClassIfStoryIsKnown() {
    masteredFrame.hasStory = true
    def cssClass = masteredFrame.getCssClass().split(" ")
    assertEquals "withStory", cssClass[2]
  }

  @Test
  void hasWithoutStoryClassIfStoryIsKnown() {
    masteredFrame.hasStory = false
    def cssClass = masteredFrame.getCssClass().split(" ")
    assertEquals "withoutStory", cssClass[2]
  }
}