package butatopanto.kanji

class MasteryServiceObjectMother {

  def activatedChapters = []

  def service = [
    activateChapter: {
      activatedChapters.add it
    }
  ]

  void setNoDueFramesIdsForChapter() {
    service["listDueFrameIdsForChapter"] = {
      []
    }
  }

  void setNoDueFrameIds() {
    setDueFrameIds([])
  }

  void setDueFrameIds(List due) {
    service["listDueFrameIds"] = {
      due
    }
  }
}
