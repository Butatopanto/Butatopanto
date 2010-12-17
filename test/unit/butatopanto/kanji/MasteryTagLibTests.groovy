package butatopanto.kanji

import butatopanto.sharedtest.TagLibJUnit4TestCase
import butatopanto.sharedtest.TagLibUtilities
import org.junit.Test

class MasteryTagLibTests extends TagLibJUnit4TestCase {

  MasteryTagLibTests() {
    super(MasteryTagLib)
  }

  @Test
  void hasRightDirectionForNextKanji() {
    def navigation = new ChapterNavigation(chapterNumber: 1, startIndex: 20)
    tagLib.linkForNextKanji(navigation: navigation);
    def content = TagLibUtilities.getContentAsString(tagLib)
    assertEquals("/mastery/listByChapter/1?startIndex=30", content)
  }

  @Test
  void hasRightDirectionForPreviousKanji() {
    def navigation = new ChapterNavigation(chapterNumber: 1, startIndex: 20)
    tagLib.linkForPreviousKanji(navigation: navigation);
    def content = TagLibUtilities.getContentAsString(tagLib)
    assertEquals("/mastery/listByChapter/1?startIndex=10", content)
  }

  @Test
  void staysInTheChapterWhenGettingNext() {
    def navigation = new ChapterNavigation(chapterNumber: 3)
    tagLib.linkForNextKanji(navigation: navigation);
    def content = TagLibUtilities.getContentAsString(tagLib)
    assertEquals("/mastery/listByChapter/3?startIndex=10", content)
  }

  @Test
  void staysInTheChapterWhenGettingPrevious() {
    def navigation = new ChapterNavigation(chapterNumber: 3)
    tagLib.linkForPreviousKanji(navigation: navigation);
    def content = TagLibUtilities.getContentAsString(tagLib)
    assertEquals("/mastery/listByChapter/3?startIndex=-10", content)
  }
}
