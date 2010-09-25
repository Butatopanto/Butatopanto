package butatopanto.bootstrap.kanji

import butatopanto.kanji.Frame

class Lesson {
  private def lessonNumber

  Lesson(def number) {
    this.lessonNumber = number
  }

  def insertFrame(number, character, meaning) {
    Frame frame = Frame.findByNumber(number)
    if (frame != null) {
      return;
    }
    new Frame(number: number, kanji: character, meaning: meaning, lesson: lessonNumber).save();
  }
}