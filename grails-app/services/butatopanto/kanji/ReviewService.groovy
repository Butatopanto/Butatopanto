package butatopanto.kanji

class ReviewService {
  def random = new Random()

  def start(Review review) {
    review.remainingIds = Frame.list()*.id
    review.totalFrameCount = review.remainingIds.size()
    nextFrame(review)
  }

  Frame getCurrentFrame(Review review) {
    review.currentId == null ? null : Frame.findById(review.currentId)
  }

  def resolve(Review review, def correct) {
    def reviewList = correct ? review.correctReviews : review.incorrectReviews
    reviewList.add(review.currentId)
    nextFrame(review)
  }

  private def nextFrame(Review review) {
    review.remainingIds.remove(review.currentId)
    review.currentId = getRandomId(review)
    return getCurrentFrame(review)
  }

  private def getRandomId(Review review) {
    if (review.remainingIds) {
      def index = random.nextInt(review.remainingIds.size())
      return review.remainingIds[index]
    }
    null
  }
}