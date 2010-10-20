package butatopanto.kanji

class Frame {
  int number
  String meaning
  String kanji
  int chapter

  static constraints = {
    number(min: 1, unique: true)
    kanji(blank: false, unique: true, maxSize: 1)
    meaning(blank: false, unique: true)
    chapter(min: 1)
  }

  static mapping  = {
    sort "number"
  }

  String toString() {
    number + ": " + kanji + " (" + meaning + ")"
  }
}
