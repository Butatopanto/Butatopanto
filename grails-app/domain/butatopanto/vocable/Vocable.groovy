package butatopanto.vocable

class Vocable {

  String meaning
  String kana
  String kanji

  static constraints = {
    meaning(blank: false)
    kana(blank: false)
    kanji(nullable: true)
  }

  static hasMany = [lists: Studylist]

  static belongsTo = Studylist

  String toString() {
    meaning
  }
}
