package butatopanto.kanji

import butatopanto.security.User

class FrameReview {
  static belongsTo = [userData: UserData]
  int box = 1
  int passed = 0
  int failed = 0
  Date dateCreated
  Date lastUpdated

  static constraints = {
  }
}
