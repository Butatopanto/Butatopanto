package butatopanto.kanji

class UserData {

  static hasMany = [masteryList: MasteryOfFrame, storyList: Story]
  String userName

  static constraints = {
    userName(unique: true)
    masteryList(validator: {list, userData ->
      def frameIdList = list.collect { it.frame.id }
      def frameIdSet = frameIdList as Set
      frameIdList.size() == frameIdSet.size()
    })
    storyList(validator: {list, userData ->
      def frameIdList = list.collect { it.frame.id }
      def frameIdSet = frameIdList as Set
      frameIdList.size() == frameIdSet.size()
    })
  }

  private def validateOncePerFrame = {list, userData ->
    def frameIdList = list.collect { it.frame.id }
    def frameIdSet = frameIdList as Set
    frameIdList.size() == frameIdSet.size()
  }
}
