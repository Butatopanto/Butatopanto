package butatopanto.kanji.bootstrap

import butatopanto.kanji.Frame
import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test
import org.junit.Ignore

class MissingChapterServiceTests extends GrailsJUnit4TestCase {

  @Before
  void mockDomainClasses() {
    mockDomain Frame.class
  }

  @Test
  void hasAllChaptersWhenDatabaseIsEmpty() {
    def missingChapters = new MissingChapterService().missingChapters()
    assertEquals 15, missingChapters.size()
  }

  @Test
  void doesNotInitializeExistingChapters() {
    new ChapterContent1().insertFrames()
    def missingChapters = new MissingChapterService().missingChapters()
    assertEquals 14, missingChapters.size()
  }

  @Test
  void hasOnlyChaptersThatHaveNotPreviouslyBeenInitialized() {
    new ChapterContent2().insertFrames()
    def missingChapters = new MissingChapterService().missingChapters()
    assertTrue missingChapters[0] instanceof ChapterContent1
  }

  @Test
  void worksForAllChapters() {
    new ChapterContent15().insertFrames()
    def missingChapters = new MissingChapterService().missingChapters()
    assertTrue missingChapters[missingChapters.size() - 1] instanceof ChapterContent14
  }
}
