package butatopanto

import grails.test.TagLibUnitTestCase

class ReviewTagLibTests extends TagLibUnitTestCase {

  protected void setUp() {
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testCreatesChapterNumber() {
    def tag = tagLib.chapter(number: 1, activeFrames: 0, totalFrames: 1)
    def text = tag.getBuffer().toString()
    def html = new XmlSlurper().parseText(text)
    assertEquals("1", html.p[0].text())
  }

  void testWritesChapterLarger() {
    def tag = tagLib.chapter(number: 1, activeFrames: 0, totalFrames: 1)
    def text = tag.getBuffer().toString()
    def html = new XmlSlurper().parseText(text)
    assertEquals ("font-size: 20px", html.p[0].@style.text())
  }

  void testCreatesKanjiCounter() {
    def tag = tagLib.chapter(number: 1, activeFrames: 0, totalFrames: 1)
    def text = tag.getBuffer().toString()
    def html = new XmlSlurper().parseText(text)
    assertEquals("0 / 1", html.p[1].text())
  }
}
