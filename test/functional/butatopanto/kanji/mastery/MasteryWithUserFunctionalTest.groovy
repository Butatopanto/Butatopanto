package butatopanto.kanji.mastery

import butatopanto.sharedtest.UserSensitiveFunctionalTestCase
import com.gargoylesoftware.htmlunit.html.FrameWindow

class MasteryWithUserFunctionalTest extends UserSensitiveFunctionalTestCase {

  void setUp() {
    super.setUp()
    logInDefaultUser()
    goToListOfSecondChapterPage()
  }

  void testShowsRequestedChapter() {
    assertTitle "Chapter 2"
  }

  void testShowsPreviousChapterAfterClickingPrevious() {
    click 'previous'
    assertTitle 'Chapter 1'
  }

  void testShowsNextChapterAfterClickingNext() {
    click 'next'
    assertTitle 'Chapter 3'
  }

  void testDoesNotShowFlipDownForShortChapter() {
    assertNull byId('flip-down')
  }

  void testDoesNotShowFlipUpForStartIndexZero() {
    assertNull byId('flip-up')
  }

  void testShowsFlipDownForChapterWithMoreThan70Kanji() {
    goToChapterWith80Kanji()
    assertNotNull byId('flip-down')
  }

  void testDoesNotShowThe71stKanjiOfLongChapter() {
    goToChapterWith80Kanji()
    assertContentDoesNotContain '欠'
  }

  void testDoesShowTheNextTenKanjiOfAChapterAfterClickOnOverrun() {
    goToChapterWith80Kanji()
    click 'flip-down'
    assertContentContains '欠'
    assertContentContains '諮'
  }

  void testDoesNotShowTheFirstTenKanjiOfAChapterAfterClickOnOverrun() {
    goToChapterWith80Kanji()
    click 'flip-down'
    assertContentDoesNotContain '衣'
    assertContentDoesNotContain '布'
  }

  void testDoesNotShowOverrunSymbolAfterFlippingToEndOfLongChapter() {
    goToChapterWith80Kanji()
    click 'flip-down'
    assertNull byId('flip-down')
  }

  void testShowsEmptyKanjiListForVeryHighStartIndex() {
    get("/mastery/listByChapter/1?startIndex=100")
    assertTitle 'Chapter 1'
  }

  void testShowsStoryEditorOnClick() {
    clickAndWait "古"
    def windows = getClient().getWebWindows()
    def window = windows.find {
      def isAFrameWindow = it instanceof FrameWindow
      if (!isAFrameWindow) {
        return false
      }
      def showsStoryEditor = it.frameElement.getAttribute('src') == '/ButatoPanto/story/show/16'
      return showsStoryEditor
    }
    assertNotNull(window)
  }

  private void clickAndWait(String identifier) {
    click identifier
    getClient().waitForBackgroundJavaScript(10000)
  }

  private def goToChapterWith80Kanji() {
    goToListPageForChapter 18
  }

  private void goToListOfSecondChapterPage() {
    goToListPageForChapter 2
  }

  private void goToListPageForChapter(int chapter) {
    get("/mastery/listByChapter/${chapter}")
  }
}
