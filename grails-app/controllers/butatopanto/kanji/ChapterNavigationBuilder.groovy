package butatopanto.kanji

class ChapterNavigationBuilder {

  int visibleFrameCount = 70
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
    navigation.masteredFrames = frames
    navigation.startIndex = startIndex
    return navigation
  }
}
