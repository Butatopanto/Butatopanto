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
    click 'to1'
    assertTitle 'Chapter 1'
  }

  void testHasButtonForThreeChaptersInAdvance() {
    click 'to5'
    assertTitle 'Chapter 5'
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
    getClient().waitForBackgroundJavaScript(10000)
    assertNotNull byId("storyBox")
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
