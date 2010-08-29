package butatopanto.kanji

class FrameController {

  def frameService
  def scaffold = Frame

  def train = {
    [frame: frameService.getRandomFrame()]
  }

  def next = {
    def frame = frameService.getRandomFrame()
    render heisig.frameCard([frame: frame, hidden: true])
  }

  def reveal = {
    def frame = Frame.findById(params.id)
    render heisig.frameCard([frame: frame, hidden: false])
  }
}
