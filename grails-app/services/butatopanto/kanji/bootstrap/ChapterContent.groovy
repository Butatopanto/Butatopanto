package butatopanto.kanji.bootstrap

import butatopanto.kanji.Frame

class ChapterContent {
  private def chapterNumber

  ChapterContent(def number) {
    this.chapterNumber = number
  }

  def insertFrame(number, character, meaning) {
    Frame frame = Frame.findByNumber(number)
    if (frame != null) {
      return;
    }
    new Frame(number: number, kanji: character, keyword: meaning, chapter: chapterNumber).save();
  }
}