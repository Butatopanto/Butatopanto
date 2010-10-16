package butatopanto

import butatopanto.kanji.bean.ChapterSelection
import butatopanto.test.TagLibJUnit4TestCase
import org.junit.Before
import org.junit.Test

class ChapterTagTest extends TagLibJUnit4TestCase {

  def html

  ChapterTagTest() {
    super(ReviewTagLib)
  }

  @Before
  public void readHtml() {
    ChapterSelection chapter = new ChapterSelection(chapterNumber: 1, totalFrames: 1)
    def tag = tagLib.chapter(chapter: chapter)
    def text = tag.getBuffer().toString()
    html = new XmlSlurper().parseText(text)
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
