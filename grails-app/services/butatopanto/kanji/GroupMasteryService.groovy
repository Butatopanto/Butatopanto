package butatopanto.kanji

class GroupMasteryService {

  static transactional = true
  def leitnerService
  def masteryQueryService

  int[] listDueFrameIds(heisigUsers) {
    def masteries = heisigUsers.collect({ masteryQueryService.listMasteriesByUsername(it)}).flatten()
    def dueMasteries = masteries.findAll {leitnerService.isDue it}
    dueMasteries.collect {it.frame.number} as Set
  }
}
