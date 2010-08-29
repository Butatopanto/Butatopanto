package butatopanto.kanji

import grails.test.ControllerUnitTestCase
import butatopanto.HeisigTagLib

class FrameControllerIntegrationTest extends ControllerUnitTestCase {

  void testRendersNewCardOnCallToNext() {
    def randomFrame = new Frame()
    controller.frameService = [getRandomFrame: {randomFrame}]
    controller.next()
    def html = controller.response.contentAsString
    assertEquals new HeisigTagLib().frameCard([frame: randomFrame]), html
  }
}
