package butatopanto

import butatopanto.kanji.bean.ChapterSelection
import grails.test.TagLibUnitTestCase

class ReviewTagLibTests extends TagLibUnitTestCase {

  def html

  protected void setUp() {
    super.setUp()
    ChapterSelection chapter = new ChapterSelection(chapterNumber: 1, totalFrames: 1)
    def tag = tagLib.chapter(chapter: chapter)
    def text = tag.getBuffer().toString()
    html = new XmlSlurper().parseText(text)
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testCreatesChapterNumber() {
    assertEquals("1", html.p[0].text())
  }

  void testWritesChapterLarger() {
    assertEquals("font-size: 20px", html.p[0].@style.text())
  }

  void testCreatesKanjiCounter() {
    assertEquals("1 Kanji", html.p[1].text())
  }
}
