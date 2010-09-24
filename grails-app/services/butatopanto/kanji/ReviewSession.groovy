package butatopanto.kanji

class ReviewSession {
  def random = new Random()
  def remainingIds
  def currentId

  def start() {
    remainingIds = Frame.list()*.id
    nextFrame()
  }

  Frame getCurrentFrame() {
    currentId == null ? null : Frame.findById(currentId)
  }

  def resolve(def correct) {
    nextFrame()
  }

  private def nextFrame() {
    remainingIds.remove(currentId)
    currentId = getRandomId()
    return getCurrentFrame()
  }

  private def getRandomId() {
    if (remainingIds) {
      def index = random.nextInt(remainingIds.size())
      return remainingIds[index]
    }
    null
  }
}