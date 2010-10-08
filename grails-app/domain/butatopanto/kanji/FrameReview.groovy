package butatopanto.kanji

import butatopanto.security.User

class FrameReview {
  def static FIRST_BOX = 1
  static belongsTo = [userData: UserData]
  Frame frame
  int box = FIRST_BOX
  int passed = 0
  int failed = 0
  Date dateCreated
  Date lastUpdated

  static constraints = {
  }
}