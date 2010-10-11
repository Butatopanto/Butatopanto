package butatopanto.kanji

class UserData {
  
  static hasMany = [masteryList: MasteryOfFrame]
  String userName
  
  static constraints = {
    userName(unique: true)
  }
}
