package butatopanto.kanji.bootstrap

import butatopanto.kanji.MasteryOfFrame
import butatopanto.learning.LeitnerService

class LeitnerMigrationService {

  static transactional = true
  LeitnerService leitnerService

  def updateDueDates() {
    MasteryOfFrame.list().each {
      if (it.dueDate == null) {
        Date lastUpdated = it.lastUpdated
        lastUpdated.clearTime()
        leitnerService.updateDueDateForMasteryAndReviewDate(it, lastUpdated)
        it.save(flush: true)
      }
    }
  }
}
