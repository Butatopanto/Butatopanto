package butatopanto.kanji

import grails.test.GrailsUnitTestCase

class HeisigContentBootstrappingTest extends GrailsUnitTestCase {

  def heisigContentService;

  public void testHasAllFramesAfterInitialization() {
    assertKnowsAllDefaultFrames()
  }

  public void testRepeatedInitializationOfHeisigContentDoesNotAddDuplicates() {
    heisigContentService.initializeDatabase()
    assertKnowsAllDefaultFrames()
  }

  private def assertKnowsAllDefaultFrames() {
    assertEquals 126, Frame.list().size()
  }
}