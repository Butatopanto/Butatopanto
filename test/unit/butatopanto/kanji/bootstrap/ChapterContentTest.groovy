package butatopanto.kanji.bootstrap

import butatopanto.kanji.Frame
import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class ChapterContentTest extends GrailsJUnit4TestCase {

  class ChapterContentDummy extends ChapterContent {

    boolean called = false;

    ChapterContentDummy() {
      super(1)
    }

    @Override
    public void addFramesToDatabase() {
      called = true
    }
  }

  @Before
  void mockDomainClasses() {
    mockDomain Frame.class
  }

  @Test
  void addsContentWhenDatabaseIsEmpty() {
    ChapterContent content = new ChapterContentDummy()
    content.insertFrames()
    assertTrue content.called
  }

  @Test
  void doesNotAddContentIfFramesFromChapterExist() {
    new ChapterContent1().insertFrames();
    ChapterContent content = new ChapterContentDummy()
    content.insertFrames()
    assertFalse content.called
  }
}
