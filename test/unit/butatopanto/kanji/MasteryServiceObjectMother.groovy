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
}
