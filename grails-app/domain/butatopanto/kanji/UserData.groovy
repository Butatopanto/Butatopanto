package butatopanto.kanji

class UserData {

  static hasMany = [masteryList: MasteryOfFrame]
  String userName

  static constraints = {
    userName(unique: true)
    masteryList(validator: {masteryList, userData ->
      def masteredFrameIdList = masteryList.collect { it.frame.id }
      def masteredFrameIdSet = masteredFrameIdList as Set
      masteredFrameIdList.size() == masteredFrameIdSet.size()
    })
  }
}
