package butatopanto.kanji

class Story {

  static belongsTo = [userData: UserData]
  Frame frame
  String text
  Date dateCreated
  Date lastUpdated

  static constraints = {
    userData(nullable: false)
    text(nullable: true, blank: true)
    frame(nullable: false)
  }
}
