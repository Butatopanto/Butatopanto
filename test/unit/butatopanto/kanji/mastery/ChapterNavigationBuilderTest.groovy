package butatopanto.kanji.mastery

import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test
import butatopanto.kanji.ChapterNavigationBuilder
import butatopanto.kanji.ChapterNavigation
import butatopanto.kanji.MasteredFrame
import butatopanto.kanji.Frame

class ChapterNavigationBuilderTest extends GrailsJUnit4TestCase {

  private int lastChapter = 5
  private ChapterNavigationBuilder builder = new ChapterNavigationBuilder(lastChapter)

  @Test
  void usesSetChapterNumber() {
    builder.setChapterNumber 3
    assertEquals 3, build().chapterNumber
  }

  @Test
  void adjustsLowChapterNumberToFirstChapter() {
    builder.setChapterNumber 0
    assertEquals 1, build().chapterNumber
  }

  @Test
  void adjustsHighChapterNumberToLastChapter() {
    builder.setChapterNumber 6
    assertEquals lastChapter, build().chapterNumber
  }

  @Test
  void hasChapterNumberBeforeSetChapterAsPrevious() {
    builder.setChapterNumber 3
    assertEquals 2, build().previous
  }

  @Test
  void hasNoPreviousChapterForFirstChapter() {
    builder.setChapterNumber 1
    assertNull build().previous
  }

  @Test
  void hasChapterNumberAfterSetChapterAsNext() {
    builder.setChapterNumber 3
    assertEquals 4, build().next
  }

  @Test
  void hasNoNextChapterForLastChapter() {
    builder.setChapterNumber lastChapter
    assertNull build().next
  }

  @Test
  void usesSetStartIndex() {
    builder.setStartIndex 6
    assertEquals 6, build().startIndex
  }

  @Test
  void adjustsLowStartIndexToZero() {
    builder.setStartIndex(-1)
    assertEquals 0, build().startIndex
  }

  @Test
  void adjustInvalidStartIndexToZero() {
    builder.setStartIndex(null)
    assertEquals 0, build().startIndex
  }

  @Test
  void showsAllFramesForFew() {
    def frames = createFrameList(2)
    builder.setFrames frames
    assertEquals frames, build().visibleFrames
  }

  @Test
  void showsLeadingFramesForManyAndStartIndexZero() {
    def frames = createFrameList(builder.visibleCount + 1)
    builder.setFrames frames
    builder.setStartIndex 0
    assertEquals frames[0..69], build().visibleFrames
  }

  @Test
  void showsTrailingFramesForManyAndStartIndexTen() {
    def frames = createFrameList(builder.visibleCount + 1)
    builder.setFrames frames
    builder.setStartIndex 10
    assertEquals frames[10..frames.size() - 1], build().visibleFrames
  }

  @Test
  void isNotOverrunForNumberOfVisibleFramesAndStartIndexZero() {
    builder.setFrames createFrameList(builder.visibleCount)
    builder.setStartIndex 0
    assertFalse build().isOverrun()
  }

  @Test
  void isOverrunForMoreFramesThanVisibleAndStartIndexZero() {
    builder.setFrames createFrameList(builder.visibleCount + 1)
    builder.setStartIndex 0
    assertTrue build().isOverrun()
  }

  @Test
  void isNotOverrunForOneFrameMoreThanVisibleAndStartIndexOfOne() {
    builder.setFrames createFrameList(builder.visibleCount + 1)
    builder.setStartIndex 1
    assertFalse build().isOverrun()
  }

  @Test
  void isNotUnderrunForStartIndexZero() {
    builder.setStartIndex 0
    assertFalse build().isUnderrun()
  }

  @Test
  void isUnderrunForStartIndexAboveZero() {
    builder.setStartIndex 1
    assertTrue build().isUnderrun()
  }

  private def createFrameList(int size) {
    def frames = []
    for (i in 1..size) {
      frames.add(new MasteredFrame(frame: new Frame(number: i, id: i)))
    }
    return frames
  }

  private ChapterNavigation build() {
    return builder.build()
  }
}
