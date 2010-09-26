package butatopanto.kanji.bootstrap

import butatopanto.kanji.Frame

class LessonContent {
  private def lessonNumber

  LessonContent(def number) {
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