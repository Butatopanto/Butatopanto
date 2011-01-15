package butatopanto.kanji

class ChapterSelectionEvaluation {

  List<ChapterSelection> chapters = []

  List<ChapterSelection> getSelectedChapterNumbers() {
    Collection selectedChapters = getSelectedChapters()
    selectedChapters.collect {it.chapterNumber}
  }

  boolean hasDueSelected() {
    haveTheseChaptersAnyKanjiDue selectedChapters
  }

  boolean hasChaptersSelected() {
    chapters.find {it.selected}
  }

  boolean hasDueFrames() {
    haveTheseChaptersAnyKanjiDue chapters
  }

  def getChapterForNumber(int chapterNumber) {
    chapters.find {
      it.chapterNumber == chapterNumber
    }
  }

  private boolean haveTheseChaptersAnyKanjiDue(def chapters) {
    chapters.find {
      it.dueFrameCount > 0
    }
  }

  private List<ChapterSelection> getSelectedChapters() {
    chapters.findAll {it.selected}
  }
}
