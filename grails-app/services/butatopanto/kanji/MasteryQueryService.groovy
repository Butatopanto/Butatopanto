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
          'in'('lesson', numbers)
        }
      }
    }
  }

  def listMastery() {
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

  private String getCurrentUserName() {
    userService.currentUser.username
  }
}
