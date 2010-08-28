package butatopanto.kanji

class Kanji {

  String character

  static constraints = {
    character(blank: false, unique: true, maxSize: 1)
  }

  String toString() {
      character
  }
}