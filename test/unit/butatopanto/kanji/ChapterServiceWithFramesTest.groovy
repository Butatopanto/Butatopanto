package butatopanto.kanji;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class ChapterServiceWithFramesTest extends GrailsJUnit4TestCase {

  private ChapterService service = new ChapterService()

  @Before
  void mockDomainClasses() {
    mockDomain Frame.class, [new Frame(id: 1, chapter: 1), new Frame(id: 2, chapter: 1), new Frame(id: 3, chapter: 3)]
  }

  @Test
  void findsNumberOfChaptersAccordingToNumberOfDifferentChapterNumbers() {
    assertEquals 2, service.findAll().size()
  }

  @Test
  void sortsChapterByNumber() {
    assertEquals([1, 3], service.findAll().collect { it.number })
  }

  @Test
  void findsChapterWithFramesForNumberOne() {
    assertEquals([1, 2], service.findAll()[0].frameIds)
  }

  @Test
  void findsChapterWithFrameForNumberTwo() {
    assertEquals([3], service.findAll()[1].frameIds)
  }

  @Test
  void hasNoLastChapter() {
    assertEquals 3, service.getLastChapterNumber()
  }
}