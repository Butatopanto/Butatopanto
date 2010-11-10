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

  private def assertKnowsAllDefaultFrames() {
    assertEquals 352, Frame.list().size()
  }
}