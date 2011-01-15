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
    }
  }

  boolean hasSelectedChapter() {
    chapters.find {it.selected}
  }

  boolean hasDue() {
    chapters.find {
      it.dueFrameCount > 0
    }
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
