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
    tagLib.class.metaClass.progress = [renderProgressBar: {"<progressbar/>"}]
    tagLib.interaction(hidden: true, frame: new Frame(id: 4, number: 5, kanji: 'k', keyword: 'the keyword'))
    String content = tagLib.out.getBuffer().toString()
    wrappedContentXml = getWrappedContentAsXml(tagLib)
  }

  @Before
  void configureRevealMessage() {
    messageCodes['frame.revealMessage'] = configuredRevealMessage
  }

  @Test
  void rendersProgressBarOnFirstPosition() {
    assertEquals 'progressbar', wrappedContentXml.children().getAt(0).name()
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
    wrappedContentXml.children().getAt(1)
  }
}