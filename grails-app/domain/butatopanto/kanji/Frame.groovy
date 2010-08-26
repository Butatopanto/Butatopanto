package butatopanto.kanji

class Frame {
  int number
  String meaning
  Kanji kanji

  static constraints = {
    meaning(blank: false, unique: true)
    number(min: 1, unique: true)
    kanji(unique: true)
  }
}
