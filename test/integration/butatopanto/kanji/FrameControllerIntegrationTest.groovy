package butatopanto.kanji

import butatopanto.HeisigTagLib
import grails.test.ControllerUnitTestCase

class FrameControllerIntegrationTest extends ControllerUnitTestCase {

  def frame = Frame.findById(1)

  void testCreatesNewHiddenCardOnCallToNext() {
    controller.frameService = [getRandomFrame: {frame}]
    controller.next()
    assertCardIsCreated true
  }

  void testRevealsCardOnRequest() {
    controller.params.id = frame.id
    controller.reveal()
    assertCardIsCreated false;
  }

  private def assertCardIsCreated(boolean hidden) {
    def html = controller.response.contentAsString
    assertEquals new HeisigTagLib().frameCard([frame: frame, hidden: hidden]), html
  }
}
