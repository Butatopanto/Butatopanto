package butatopanto.kanji

import butatopanto.security.User

class Story {

  static belongsTo = [userData: UserData]
  Frame frame
  String text

  static constraints = {
    userData(nullable: false)
    text(nullable: true, blank: true)
    frame(nullable: false)
  }
}
