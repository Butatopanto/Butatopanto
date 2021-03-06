package butatopanto.kanji;


import butatopanto.sharedtest.TagLibJUnit4TestCase
import org.junit.Before
import org.junit.Test

class ChapterSelectionTagTest extends TagLibJUnit4TestCase {

  ChapterSelectionTagTest() {
    super(ReviewTagLib)
  }

  private ChapterSelection chapter = new ChapterSelection(chapterNumber: 1, totalFrames: 15, dueFrameCount: 6)

  @Before
  void setDuePattern() {
    messageCodes["review.assemble.dueCount"] = "{0} fällig"
  }

  @Test
  void rendersLinkForInactiveChapterWithInactiveCssClass() {
    chapter.active = false
    assertContainsCssClass(createLinkHtml(), "inactive")
  }

  @Test
  void rendersLinkForSelectedChapterWithSelectedCssClass() {
    chapter.selected = true
    assertContainsCssClass(createLinkHtml(), "selected")
  }

  @Test
  void rendersLinkForDeselectedChapterWithDeselectedCssClass() {
    chapter.selected = false
    assertContainsCssClass(createLinkHtml(), "deselected")
  }

  @Test
  void rendersLinkToRemoveLessonForSelectedLesson() {
    chapter.selected = true
    def html = createLinkHtml()
    assertEquals("/link/to/removeChapter/1", html.@href.text())
  }

  @Test
  void rendersLinkToAddLessonForDeselectedLesson() {
    chapter.selected = false
    def html = createLinkHtml()
    assertEquals("/link/to/addChapter/1", html.@href.text())
  }

  private def createLinkHtml() {
    def tag = tagLib.chapterSelector(chapter: chapter)
    def text = tag.getBuffer().toString()
    new XmlSlurper().parseText(text)
  }

  def assertContainsCssClass(def element, def expectedClass) {
    String classAttribute = element.@"class".text()
    List allClasses = classAttribute.split(" ")
    assertTrue("Found css-class " + classAttribute, allClasses.contains(expectedClass))
  }
}