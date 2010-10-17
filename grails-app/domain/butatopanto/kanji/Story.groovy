package butatopanto.kanji

class Story {

  static belongsTo = [user: HeisigUser]
  Frame frame
  String text
  Date dateCreated
  Date lastUpdated

  static constraints = {
    user(nullable: false)
    text(nullable: true, blank: true)
    frame(nullable: false)
  }
}
