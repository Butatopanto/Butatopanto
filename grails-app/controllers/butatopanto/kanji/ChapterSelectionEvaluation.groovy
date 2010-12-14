package butatopanto.kanji

class ChapterSelectionEvaluation {

  List<ChapterSelection> chapters = []

  List<ChapterSelection> getSelectedChapterNumbers() {
    Collection selectedChapters = getSelectedChapters()
    selectedChapters.collect {it.chapterNumber}
  }

  boolean hasDueSelected() {
    def selectedChapters = getSelectedChapters()
    selectedChapters.find { 
      it.dueFrameCount > 0
    } != null
  }

  def hasSelectedChapter() {
    chapters.find {it.selected} != null
  }

  private List<ChapterSelection> getSelectedChapters() {
    chapters.findAll {it.selected}
  }

  def getChapterForNumber(int chapterNumber) {
    chapters.find {
      it.chapterNumber == chapterNumber
    }
  }
}
