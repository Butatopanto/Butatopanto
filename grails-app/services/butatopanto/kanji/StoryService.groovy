package butatopanto.kanji

import butatopanto.security.User
import org.junit.Before

class StoryService {

  static transactional = true
  def userService

  def findStoryByFrameId(def frameId) {
    def storyList = userService.currentUserData?.storyList
    if (!storyList) {
      return null
    }
    Story foundStory = storyList.find {it.frame.id == frameId}
    foundStory?.text
  }
}