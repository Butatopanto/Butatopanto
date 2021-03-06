package butatopanto.kanji;


import butatopanto.sharedtest.TagLibJUnit4TestCase
import org.junit.Before
import org.junit.Test
import static butatopanto.sharedtest.TagLibUtilities.getWrappedContentAsXml

class HeisigHiddenInteractionTagTest extends TagLibJUnit4TestCase {
  private def wrappedContentXml
  private def configuredRevealMessage = "It is magic."

  HeisigHiddenInteractionTagTest() {
    super(HeisigTagLib)
  }

  @Before
  void renderHiddenInteraction() {
    messageCodes['frame.revealMessage'] = configuredRevealMessage
    tagLib.interaction(hidden: true, frame: new Frame(id: 4, number: 5, kanji: 'k', keyword: 'the keyword'))
    tagLib.out.getBuffer().toString()
    wrappedContentXml = getWrappedContentAsXml(tagLib)
  }

  @Test
  void rendersParagraphOnSecondPosition() {
    assertEquals 'p', getRevealMessageParagraph().name()
  }

  @Test
  void rendersParagraphWithFrameMessageCssClass() {
    assertEquals 'frameMessage', getRevealMessageParagraph().@class.text()
  }
 
  @Test
  void rendersRevealFrameMessageInContent() {
    assertEquals configuredRevealMessage, getRevealMessageParagraph().text()
  }

  private def getRevealMessageParagraph() {
    wrappedContentXml.children().getAt(0).children().getAt(1).children().getAt(0)
  }
}