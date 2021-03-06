package butatopanto.kanji

import butatopanto.learning.Calendar

class MasteryQueryService {

  static transactional = true
  def userService
  def calendar = new Calendar()

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

  def listDueMasteryForChapterList(List numbers) {
    MasteryOfFrame.withCriteria {
      and {
        user {
          eq('userName', currentUserName)
        }
        frame {
          'in'('chapter', numbers)
        }
        le('dueDate', calendar.today)
      }
    }
  }

  def listDueMastery() {
    MasteryOfFrame.withCriteria {
      and {
        user {
          eq('userName', currentUserName)
        }
        le('dueDate', calendar.today)
      }
    }
  }

  def listMasteryForCurrentUser() {
    if (!userService.currentUser) {
      return []
    }
    listMasteriesByUsername currentUserName
  }

  def listMasteriesByUsername(username) {
    MasteryOfFrame.withCriteria {
      user {
        eq('userName', username)
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
