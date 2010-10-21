package butatopanto.kanji

class MasteryQueryServiceObjectMother {
  def service = [:]

  void queryFromHeisigUserData(String userName) {
    service["listMastery"] = {
      HeisigUser.findByUserName(userName).masteryList as List
    }
    service["findMasteryByFrameId"] = { frameId ->
      def masteryList = HeisigUser.findByUserName(userName).masteryList as List
      masteryList.find {
        frameId == it.frame.id
      }
    }
  }
}
