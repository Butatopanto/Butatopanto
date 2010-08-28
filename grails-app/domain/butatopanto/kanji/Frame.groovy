package butatopanto.kanji

class Frame {
  int number
  String meaning
  Kanji kanji

  static constraints = {
    number(min: 1, unique: true)
    kanji(unique: true)
    meaning(blank: false, unique: true)
  }
}
