package butatopanto.kanji

class FrameService {

  static transactional = true
  def random = new Random()

  def getRandomFrame() {
    def frames = Frame.list()
    def ids = frames*.id
    long id = getRandomId(ids)
    return id == null ? null : Frame.findById(id)
  }

  def getRandomId(List<Long> ids) {
    if (ids) {
      def index = random.nextInt(ids.size())
      return ids[index]
    }
    null
  }
}