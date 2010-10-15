package butatopanto;

import butatopanto.test.*
import org.junit.*
import butatopanto.kanji.bean.ChapterSelection

class ChapterSelectionTagTest extends TagLibJUnit4TestCase {

  ChapterSelectionTagTest() {
    super(ReviewTagLib)
  }

  @Before
  void mockLink() {
    ReviewTagLib.class.metaClass.link { arguments, body ->
      def theOut = out
      out << "<a href='/link/to/${arguments.action}/${arguments.id}'>"
      body()
      out << "</a>"
    }
  }

  @Test
  void something() {
    ChapterSelection chapter = new ChapterSelection(chapterNumber: 1, totalFrames: 1)
    def tag = tagLib.chapterSelector(chapter: chapter)
    def theOut = tagLib.out
    def text = "<root>" + tag.getBuffer().toString() + "</root>"
    def html = new XmlSlurper().parseText(text)
  }
}