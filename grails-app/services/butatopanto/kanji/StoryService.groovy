package butatopanto.kanji

class StoryService {

  static transactional = true
  def userService

  def saveStory(def frameId, def storyText) {
    Story existingStory = findStoryByFrameId(frameId)
    if (existingStory) {
      existingStory.text = storyText
      existingStory.save()
    }
    else {
      def userData = userService.findOrCreateUserData()
      def frame = Frame.findById(frameId)
      userData.addToStoryList(new Story(frame: frame, text: storyText))
    }
  }

  def findStoryTextByFrameId(def frameId) {
    Story foundStory = findStoryByFrameId(frameId)
    foundStory?.text
  }

  private def findStoryByFrameId(def frameId) {
    def storyList = userService.currentUserData?.storyList
    if (!storyList) {
      return null
    }
    storyList.find {it.frame.id == frameId}
  }
}