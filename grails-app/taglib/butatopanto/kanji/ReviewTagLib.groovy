package butatopanto.kanji

class ReviewTagLib {

  static namespace = "heisig"

  def chapterSelector = {attributes ->
    def chapterSelection = attributes.chapter
    boolean selected = chapterSelection.selected
    boolean active = chapterSelection.active
    def cssClass = 'selector'
    cssClass += active ? " active" : " inactive"
    cssClass += selected ? " selected" : " deselected"
    def clickAction = selected ? "removeChapter" : "addChapter"
    def chapterNumber = chapterSelection.chapterNumber
    out << g.link("class": cssClass, action: clickAction, id: chapterNumber, elementId: "chapter${chapterNumber}") {
      chapter(attributes)
    }
  }

  private chapter(attributes) {
    def chapterSelection = attributes.chapter
    def chapterNumber = chapterSelection.chapterNumber
    def totalFrames = chapterSelection.totalFrames
    def due = g.message(code: 'review.assemble.dueCount', args: [chapterSelection.dueFrameCount])
    render(template: '/selectorContent', model: [header: chapterNumber, firstComment: "$totalFrames Kanji", secondComment: due])
  }
}
