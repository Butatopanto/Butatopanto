package butatopanto.kanji

class ReviewSession {
  def random = new Random()
  def remainingIds
  def currentId
  def totalFrameCount

  def start() {
    remainingIds = Frame.list()*.id
    totalFrameCount = remainingIds.size()
    nextFrame()
  }

  Frame getCurrentFrame() {
    currentId == null ? null : Frame.findById(currentId)
  }

  def resolve(def correct) {
    nextFrame()
  }

  def getTotalFrameCount() {
    totalFrameCount
  }

  def getRemainingFrameCount() {
    remainingIds.size()
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