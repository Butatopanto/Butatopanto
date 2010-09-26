package butatopanto.kanji

import butatopanto.security.User

class UserData {
  
  static hasMany = [frameReviews: FrameReview]
  String userName
  
  static constraints = {
    userName(unique: true)
  }
}
