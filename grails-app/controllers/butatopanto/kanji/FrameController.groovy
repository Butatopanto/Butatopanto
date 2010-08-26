package butatopanto.kanji

class FrameController {

  def frameService
  def scaffold = Frame

  def train = {
    [frame: frameService.getRandomFrame()]
  }
}
