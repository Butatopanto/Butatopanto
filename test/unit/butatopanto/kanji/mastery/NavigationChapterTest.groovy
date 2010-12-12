package butatopanto.kanji.mastery

import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test
import butatopanto.kanji.NavigationChapter
import butatopanto.kanji.MasteredFrame

class NavigationChapterTest extends GrailsJUnit4TestCase {

  private NavigationChapter chapter = new NavigationChapter()

  @Test
  void hasNoOverrunForANumberOfVisibleFramesAndStartIndexZero() {
    chapter.startIndex = 0
    chapter.masteredFrames = createFrameList(chapter.visibleCount)
    assertFalse chapter.isOverrun()
  }

  @Test
  void hasOverrunForOneFrameMoreThanVisibleAndStartIndexZero() {
    chapter.startIndex = 0
    chapter.masteredFrames = createFrameList(chapter.visibleCount + 1)
    assertTrue chapter.isOverrun()
  }

  @Test
  void hasNoOverrunForOneFrameMoreThanVisibleAndStartIndexOfOne() {
    chapter.startIndex = 1
    chapter.masteredFrames = createFrameList(chapter.visibleCount + 1)
    assertFalse chapter.isOverrun()
  }

  @Test
  void isNotUnderrunForStartIndexOfZero() {
    chapter.startIndex = 0
    assertFalse chapter.isUnderrun()
  }

  @Test
  void isUnderrunForStartIndexOfOne() {
    chapter.startIndex = 1
    assertTrue chapter.isUnderrun()
  }

  def createFrameList(int size) {
    def frames = []
    for (i in 1..size) {
      frames.add(new MasteredFrame())
    }
    frames
  }
}
