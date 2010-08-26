package butatopanto.kanji

class Kanji {

  String character
  String meaning

  static constraints = {
    character(blank: false, unique: true, maxSize: 1)
    meaning(blank: false, unique: true)
  }

  String toString() {
      character
  }
}
