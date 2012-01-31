package butatopanto.kanji.bootstrap

import butatopanto.kanji.Frame

abstract class ChapterContent {

  private def chapterNumber

  ChapterContent(def number) {
    this.chapterNumber = number
  }

  public void insertFrames() {
    if (thereAreNoFramesFromChapter()) {
      addFramesToDatabase()
    }
  }

  boolean thereAreNoFramesFromChapter(){
    !Frame.findByChapter(chapterNumber)
  }

  abstract void addFramesToDatabase()

  def insertFrame(number, character, meaning) {
    new Frame(number: number, kanji: character, keyword: meaning, chapter: chapterNumber).save();
  }

  def updateKeyword(number, keyword) {
      def frame = Frame.findByNumber(number)
      frame.setKeyword(keyword)
      frame.save(flush: true)
  }
}
