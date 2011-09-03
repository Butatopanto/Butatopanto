package butatopanto.kanji;


import butatopanto.sharedtest.TagLibJUnit4TestCase
import org.junit.Before
import org.junit.Test
import static butatopanto.sharedtest.TagLibUtilities.getWrappedContentAsXml

class HeisigRevealedInteractionTagTest extends TagLibJUnit4TestCase {

  private def wrappedContentXml
  private def configuredReviewResultQuestion = "Wie sieht es aus?"
  def configuredConfirmMessage = 'confirmMessage'
  def configuredDeclineMessage = 'declineMessage'

  HeisigRevealedInteractionTagTest() {
    super(HeisigTagLib)
  }

  @Before
  void renderHiddenInteraction() {
    configureMessages()
    tagLib.interaction(hidden: false, frame: new Frame(id: 4, number: 5, kanji: 'k', keyword: 'the keyword'))
    wrappedContentXml = getWrappedContentAsXml(tagLib)
  }

  private def configureMessages() {
    messageCodes['frame.reviewResultQuestion'] = configuredReviewResultQuestion
    messageCodes['frame.reviewResult.confirm'] = configuredConfirmMessage
    messageCodes['frame.reviewResult.decline'] = configuredDeclineMessage
  }

  @Test
  void rendersDivOnSecondPosition() {
    assertEquals 'div', getResultButtonDiv().name()
  }

  @Test
  void rendersDivWithFrameMessageCssClass() {
    assertEquals 'frameMessage', getResultButtonDiv().@class.text()
  }

  @Test
  void displaysResultQuestionMessageInParagraph() {
    assertEquals 'p', getMessageParagraph().name()
  }

  @Test
  void rendersReviewResultQuestionMessageInContent() {
    assertEquals configuredReviewResultQuestion, getMessageParagraph().text()
  }

  @Test
  void rendersFormAfterMessageParagraph() {
    assertEquals 'form', getResultForm().name()
  }

  @Test
  void rendersTwoSubmitsToRemoteInForm() {
    assertEquals 'submitToRemote(0) submitToRemote(1)', getResultForm().text()
  }

  @Test
  void rendersConfirmButtonFirst() {
    def url = [controller: 'review', action: 'ajaxResolve', params: [kanji: 'k', reviewCorrect: true]]
    def parameters = submitsToRemote.get(0)
    assertEquals 'closeStoryDialog()', parameters['onComplete']
    assertEquals 'container', parameters['update']
    assertEquals 'confirmButton', parameters['id']
    assertTrue(((String) parameters['class']).contains('green'))
    assertEquals configuredConfirmMessage, parameters['value']
    assertEquals url, parameters['url']
  }

  @Test
  void rendersDeclineButtonSecond() {
    def url = [controller: 'review', action: 'ajaxResolve', params: [kanji: 'k', reviewCorrect: false]]
    def parameters = submitsToRemote.get(1)
    assertEquals 'closeStoryDialog()', parameters['onComplete']
    assertEquals 'container', parameters['update']
    assertEquals 'declineButton', parameters['id']
    assertTrue(((String) parameters['class']).contains('red'))
    assertEquals configuredDeclineMessage, parameters['value']
    assertEquals url, parameters['url']
  }

  private def getMessageParagraph() {
    getResultButtonDiv().children().getAt(0)
  }

  private def getResultForm() {
    getResultButtonDiv().children().getAt(1)
  }

  private def getResultButtonDiv() {
    wrappedContentXml.children().getAt(0).children().getAt(1).children().getAt(0)
  }
}