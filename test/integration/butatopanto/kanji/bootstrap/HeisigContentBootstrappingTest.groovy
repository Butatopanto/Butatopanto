package butatopanto.kanji.bootstrap

import butatopanto.test.GrailsJUnit4TestCase
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
    junit.framework.Assert.assertEquals 15, Frame.countByLesson(1)
  }

  @Test
  void knows19FramesInLesson2() {
    assertEquals 19, Frame.countByLesson(2)
  }

  private def assertKnowsAllDefaultFrames() {
    assertEquals 126, Frame.list().size()
  }
}