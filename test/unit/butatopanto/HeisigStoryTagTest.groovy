package butatopanto

import butatopanto.sharedtest.TagLibJUnit4TestCase
import org.junit.Test
import static butatopanto.sharedtest.TagLibUtilities.getContentAsXml

class HeisigStoryTagTest extends TagLibJUnit4TestCase {

  HeisigStoryTagTest() {
    super(StoryTagLib)
  }

  @Test
  void hasParagraphAsRoot() {
    tagLib.story(text: "Wichtiger Text")
    def xml = getContentAsXml(tagLib)
    assertEquals "p", xml.name()
  }

  @Test
  void hasTextArgumentAsTextualBodyOfRoot() {
    tagLib.story(text: "Wichtiger Text")
    def xml = getContentAsXml(tagLib)
    assertEquals "Wichtiger Text", xml.text()
  }

  @Test
  void hasUnknownHintWithoutArgumentText() {
    messageCodes['review.story.unknown'] = "Ätsch!"
    tagLib.story(text: "")
    def xml = getContentAsXml(tagLib)
    assertEquals "Ätsch!", xml.text()
  }
}