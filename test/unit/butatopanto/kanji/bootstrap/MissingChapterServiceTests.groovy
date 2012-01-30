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
  void hasAllChapters() {
    def missingChapters = new MissingChapterService().allChapters()
    assertEquals 23, missingChapters.size()
  }
}
