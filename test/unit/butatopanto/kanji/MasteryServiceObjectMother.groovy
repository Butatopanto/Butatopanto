package butatopanto.kanji

class MasteryServiceObjectMother {

  def activatedChapters = []

  def service = [
    activateChapter: {
      activatedChapters.add it
    }
  ]

  void setNoDueFramesIds() {
    service["listDueFrameIdsForChapter"] = {
      []
    }
  }

  void setDueFrames() {
    service["listDueFrameIdsForChapter"] = {
      [15]
    }
  }
}
