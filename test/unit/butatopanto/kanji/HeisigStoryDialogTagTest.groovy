package butatopanto.kanji;


import butatopanto.sharedtest.TagLibJUnit4TestCase
import org.junit.Before
import org.junit.Test
import static butatopanto.sharedtest.TagLibUtilities.getWrappedContentAsXml
import butatopanto.learning.Review

class HeisigStoryDialogTagTest extends TagLibJUnit4TestCase {
  private def wrappedContentXml

  HeisigStoryDialogTagTest() {
    super(HeisigTagLib)
  }

  @Before
  void configureMessages() {
    messageCodes['frame.clickToRevealStory'] = "Click to reveal story"
    messageCodes['story.dialog.title'] = "Story"
  }

  @Test
  void rendersNothingWithoutReview() {
    tagLib.session.review = null
    def wrappedDialog = renderAndWrapStoryDialog()
    assertEquals 0, wrappedDialog.children().size()
  }

  @Test
  void hasOnClickAttributeToShowDialog() {
    tagLib.session.review = new Review()
    def wrappedDialog = renderAndWrapStoryDialog()
      def onClickText = wrappedDialog.div[0].div[0].@onclick.text()
      assertEquals "openStoryDialog('Story', '')", onClickText
  }

  private def renderAndWrapStoryDialog() {
    tagLib.storyDialog()
    tagLib.out.getBuffer().toString()
    getWrappedContentAsXml(tagLib)
  }
}
