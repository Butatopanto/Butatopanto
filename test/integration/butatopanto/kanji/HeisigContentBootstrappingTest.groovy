package butatopanto.kanji

import grails.test.GrailsUnitTestCase

class HeisigContentBootstrappingTest extends GrailsUnitTestCase {

  def heisigContentService;

  void testHasAllFramesAfterInitialization() {
    assertKnowsAllDefaultFrames()
  }

  void testRepeatedInitializationOfHeisigContentDoesNotAddDuplicates() {
    heisigContentService.initializeDatabase()
    assertKnowsAllDefaultFrames()
  }

  void testKnows15FramesInLesson1() {
    assertEquals 15, Frame.countByLesson(1)
  }

  void testKnows19FramesInLesson2() {
    assertEquals 19, Frame.countByLesson(2)
  }

  private def assertKnowsAllDefaultFrames() {
    assertEquals 126, Frame.list().size()
  }
}