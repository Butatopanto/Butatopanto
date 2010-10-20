package butatopanto.kanji;


import org.junit.*
import butatopanto.sharedtest.GrailsJUnit4TestCase

class ChapterServiceWithoutFramesTest extends GrailsJUnit4TestCase {

  private ChapterService service = new ChapterService()

  @Before
  void mockDomainClasses() {
    mockDomain Frame.class
  }

  @Test
  void findsNoChapterWithoutFrames() {
    assertEquals 0, service.findAll().size()
  }

  @Test
  void hasNoLastChapter() {
    assertEquals 0, service.getLastChapterNumber()
  }
}