package butatopanto.kanji

class ChapterNavigation {

  def visibleCount = 70
  def chapterNumber
  def previous
  def next
  int startIndex = 0
  List<MasteredFrame> masteredFrames = []

  List<MasteredFrame> getVisibleFrames() {
    def endIndex = getEndIndex()
    masteredFrames[startIndex..endIndex]
  }

  private def getEndIndex() {
    def nextStartIndex = startIndex + visibleCount
    def availableFrameCount = masteredFrames.size()
    Math.min(nextStartIndex, availableFrameCount) - 1
  }

  boolean isOverrun() {
     startIndex + visibleCount < masteredFrames.size()
  }

  boolean isUnderrun() {
    return startIndex > 0
  }
}
