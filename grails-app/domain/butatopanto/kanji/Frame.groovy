package butatopanto.kanji

class Frame {
  int number
  String meaning
  String kanji
  int lesson

  static constraints = {
    number(min: 1, unique: true)
    kanji(blank: false, unique: true, maxSize: 1)
    meaning(blank: false, unique: true)
    lesson(min: 1)
  }

  String toString() {
    number + ": " + kanji + " (" + meaning + ")"
  }
}
