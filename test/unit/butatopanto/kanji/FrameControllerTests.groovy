package butatopanto.kanji

import grails.test.ControllerUnitTestCase

class FrameControllerTests extends ControllerUnitTestCase {

  void testReturnsRandomFrameForTraining() {
    def randomFrame = new Frame()
    controller.frameService = [getRandomFrame: {randomFrame}]
    def frame = controller.train()["frame"]
    assertSame randomFrame, frame
  }
}