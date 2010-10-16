package butatopanto.kanji

class UserData {

  static hasMany = [masteryList: MasteryOfFrame, storyList: Story]
  String userName

  static constraints = {
    userName(unique: true)
    masteryList(validator: oncePerFrame)
    storyList(validator: oncePerFrame)
  }

  static def oncePerFrame = {list, userData ->
    def frameIdList = list.collect { it.frame.id }
    def frameIdSet = frameIdList as Set
    frameIdList.size() == frameIdSet.size()
  }
}
