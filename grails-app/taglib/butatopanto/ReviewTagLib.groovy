package butatopanto

class ReviewTagLib {

  static namespace = "heisig"

  def chapterSelector = {attributes ->
    boolean selected = attributes.selected
    def cssClass = selected ? "chosen" : "selectable"
    def clickAction = selected ? "removeLesson" : "addLesson"
    def chapterNumber = attributes.number
    out << g.link("class": cssClass, action: clickAction, id: chapterNumber) {
      chapter(attributes)
    }
  }

  def chapter = { attributes ->
    def chapter = attributes.number
    def totalFrames = attributes.totalFrames
    out << "<div>"
    out << "<p style='font-size: 20px'>$chapter</p>"
    out << "<p>$totalFrames Kanji</p>"
    out << "</div>"
  }
}
