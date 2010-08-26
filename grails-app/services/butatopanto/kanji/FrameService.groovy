package butatopanto.kanji

class FrameService {

  static transactional = true
  def random = new Random()

  def getRandomFrame() {
    if (Frame.list()) {
      def index = random.nextInt(Frame.count())
      return Frame.list()[index]
    }
    null
  }
}
