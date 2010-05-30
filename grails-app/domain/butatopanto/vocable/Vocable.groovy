package butatopanto.vocable

class Vocable {

  static constraints = {
    meaning(blank: false)
    kana(blank: false)
    kanji(nullable: true)
  }

  String meaning
  String kana
  String kanji
}
