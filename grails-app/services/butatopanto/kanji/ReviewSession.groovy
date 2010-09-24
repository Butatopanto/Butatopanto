package butatopanto.kanji

class ReviewSession {
  def random = new Random()
  def remainingIds
  def currentId
  def totalFrameCount
  def correctReviews = []
  def incorrectReviews = []

  def start() {
    remainingIds = Frame.list()*.id
    totalFrameCount = remainingIds.size()
    nextFrame()
  }

  Frame getCurrentFrame() {
    currentId == null ? null : Frame.findById(currentId)
  }

  def resolve(def correct) {
    def reviewList = correct ? correctReviews : incorrectReviews
    reviewList.add(currentId)
    nextFrame()
  }

  def getRemainingFrameCount() {
    remainingIds.size()
  }

  def getCorrectReviewCount() {
    correctReviews.size()
  }

  def getIncorrectReviewCount() {
    incorrectReviews.size()
  }

  def getReviewedFrameCount() {
    getTotalFrameCount() - getRemainingFrameCount()
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