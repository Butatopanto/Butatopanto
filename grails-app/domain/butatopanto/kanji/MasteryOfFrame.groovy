package butatopanto.kanji

import butatopanto.security.User
import butatopanto.learning.LeitnerService

class MasteryOfFrame {
  static belongsTo = [userData: UserData]
  Frame frame
  int box = LeitnerService.FIRST_BOX
  int passed = 0
  int failed = 0
  Date dateCreated
  Date lastUpdated

  static constraints = {
    passed(min: 0)
    failed(min: 0)
    box(min: LeitnerService.FIRST_BOX, max: LeitnerService.LAST_BOX)
  }
}