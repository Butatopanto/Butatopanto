package butatopanto.kanji

class MasteryQueryService {

  static transactional = true
  def userService

  def listMasteryForChapterList(List numbers) {
    MasteryOfFrame.withCriteria {
      and {
        user {
          eq('userName', currentUserName)
        }
        frame {
          'in'('chapter', numbers)
        }
      }
    }
  }

  def listMastery() {
    if (!userService.currentUser) {
      return []
    }
    MasteryOfFrame.withCriteria {
      user {
        eq('userName', currentUserName)
      }
    }
  }

  def findMasteryByFrameId(long frameId) {
    def masteryList = MasteryOfFrame.withCriteria {
      user {
        eq('userName', currentUserName)
      }
      frame {
        eq('id', frameId)
      }
    }
    masteryList[0]
  }

  def listMasteriesForBox(int box) {
    if (!userService.currentUser) {
      return []
    }
    MasteryOfFrame.withCriteria {
      user {
        eq('userName', currentUserName)
      }
      eq('box', box)
    }
  }

  private String getCurrentUserName() {
    userService.currentUserName
  }
}
