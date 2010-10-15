package butatopanto

class ReviewTagLib {

  static namespace = "heisig"

  def chapterSelector = {attributes ->
    def chapterSelection = attributes.chapter
    boolean selected = chapterSelection.selected
    boolean active = chapterSelection.active
    def cssClass = active ? "active" : "inactive"
    cssClass += selected ? " selected" : " deselected"
    def clickAction = selected ? "removeLesson" : "addLesson"
    def chapterNumber = chapterSelection.chapterNumber
    out << g.link("class": cssClass, action: clickAction, id: chapterNumber) {
      chapter(attributes)
    }
  }

  def chapter = { attributes ->
    def chapterSelection = attributes.chapter
    def chapterNumber = chapterSelection.chapterNumber
    def totalFrames = chapterSelection.totalFrames
    out << "<div>"
    out << "<p style='font-size: 20px'>$chapterNumber</p>"
    out << "<p>$totalFrames Kanji</p>"
    out << "<p>$chapterSelection.dueFrameCount fällig</p>"
    out << "</div>"
  }
}
