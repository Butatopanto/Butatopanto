package butatopanto.kanji

import butatopanto.kanji.ChapterSelection
import butatopanto.sharedtest.TagLibJUnit4TestCase
import org.junit.Before
import org.junit.Test
import static butatopanto.sharedtest.TagLibUtilities.*
import butatopanto.kanji.ReviewTagLib

class ChapterTagTest extends TagLibJUnit4TestCase {

  def html

  ChapterTagTest() {
    super(ReviewTagLib)
  }

  @Before
  public void readHtml() {
    ChapterSelection chapter = new ChapterSelection(chapterNumber: 1, totalFrames: 1)
    tagLib.chapter(chapter: chapter)
    html = getContentAsXml(tagLib)
  }

  @Test
  void createsChapterNumber() {
    assertEquals("1", html.p[0].text())
  }

  @Test
  void writesChapterLarger() {
    assertEquals("font-size: 20px", html.p[0].@style.text())
  }

  @Test
  void createsKanjiCounter() {
    assertEquals("1 Kanji", html.p[1].text())
  }
}
