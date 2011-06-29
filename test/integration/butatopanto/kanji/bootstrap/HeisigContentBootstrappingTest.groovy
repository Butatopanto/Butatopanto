package butatopanto.kanji.bootstrap

import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test
import butatopanto.kanji.Frame

class HeisigContentBootstrappingTest extends GrailsJUnit4TestCase {

  def heisigContentService;

  @Test
  void hasAllFramesAfterInitialization() {
    assertKnowsAllDefaultFrames()
  }

  @Test
  void doesNotAddDuplicatesOnRepeatedInitialization() {
    heisigContentService.initializeDatabase()
    assertKnowsAllDefaultFrames()
  }

  @Test
  void knows15FramesInLesson1() {
    assertEquals 15, Frame.countByChapter(1)
  }

  @Test
  void knows19FramesInLesson2() {
    assertEquals 19, Frame.countByChapter(2)
  }

  @Test
  void knows17FramesInLesson16() {
    assertEquals 17, Frame.countByChapter(16)
  }

    @Test
    void knows26FramesInLesson17() {
      assertEquals 26, Frame.countByChapter(17)
    }

    @Test
    void knows80FramesInLesson18() {
      assertEquals 80, Frame.countByChapter(18)
    }

    @Test
    void knows33FramesInLesson19() {
      assertEquals 33, Frame.countByChapter(19)
    }

  private def assertKnowsAllDefaultFrames() {
    assertEquals 577, Frame.list().size()
  }
}