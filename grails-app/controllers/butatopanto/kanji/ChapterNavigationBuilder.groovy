package butatopanto.kanji

class ChapterNavigationBuilder {

  int visibleCount = 70
  private int startIndex = 0
  private List<MasteredFrame> frames = []
  private int lastChapter
  private int currentChapter
  private def previousChapter
  private def nextChapter

  ChapterNavigationBuilder(int lastChapter) {
    this.lastChapter = lastChapter;
  }

  void setStartIndex(def startIndex) {
    if (startIndex) {
      this.startIndex = Math.max(0, startIndex)
    } else {
      this.startIndex = 0
    }
  }

  void setFrames(List<MasteredFrame> frames) {
    this.frames = frames
  }

  void setChapterNumber(int chapterNumber) {
    currentChapter = Math.max(1, chapterNumber)
    currentChapter = Math.min(lastChapter, currentChapter)
    previousChapter = currentChapter == 1 ? null : currentChapter - 1
    nextChapter = currentChapter == lastChapter ? null : currentChapter + 1
  }

  ChapterNavigation build() {
    ChapterNavigation navigation = new ChapterNavigation()
    navigation.previous = previousChapter
    navigation.next = nextChapter
    navigation.chapterNumber = currentChapter
    navigation.startIndex = startIndex
    navigation.visibleFrames = getVisibleFrames()
    navigation.overrun = isOverrun()
    navigation.underrun = isUnderrun()
    return navigation
  }

  List<MasteredFrame> getVisibleFrames() {
    if (!frames) {
      return []
    }
    def endIndex = getEndIndex()
    frames[startIndex..endIndex]
  }

  private def getEndIndex() {
    def nextStartIndex = startIndex + visibleCount
    def availableFrameCount = frames.size()
    Math.min(nextStartIndex, availableFrameCount) - 1
  }

  private boolean isOverrun() {
    startIndex + visibleCount < frames.size()
  }

  private boolean isUnderrun() {
    return startIndex > 0
  }
}
