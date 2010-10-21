package butatopanto.kanji

class ChapterSelectionEvaluation {

  List<ChapterSelection> chapters = []

  def getSelectedChapterNumbers() {
    def selectedChapters = chapters.findAll {it.selected}
    selectedChapters.collect {it.chapterNumber}
  }

  def hasSelectedChapter() {
    chapters.find {it.selected} != null
  }

  def getChapterForNumber(int chapterNumber) {
    chapters.find {
      it.chapterNumber == chapterNumber
    }
  }
}
