package butatopanto.kanji

import butatopanto.learning.LeitnerService

class MasteryOfFrame {
  static belongsTo = [user: HeisigUser]
  Frame frame
  int box = LeitnerService.FIRST_BOX
  int passed = 0
  int failed = 0
  Date dateCreated
  Date lastUpdated
  Date dueDate

  static constraints = {
    frame(nullable: false)
    passed(min: 0)
    failed(min: 0)
    box(min: LeitnerService.FIRST_BOX, max: LeitnerService.LAST_BOX)
  }
}