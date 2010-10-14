package butatopanto

class ReviewTagLib {

  static namespace = "heisig"

  def chapterSelector = {attributes ->
    def chapterSelection = attributes.chapter
    boolean selected = chapterSelection.selected
    boolean active = chapterSelection.active
    def cssClass = active ? "active" : "inactive"
    cssClass += selected ? "_selected" : "_unselected"
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
    out << "<P>$chapterSelection.dueFrameCount fällig</P>"
    out << "</div>"
  }
}
