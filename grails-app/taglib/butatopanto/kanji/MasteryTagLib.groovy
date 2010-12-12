package butatopanto.kanji

class MasteryTagLib {

  static namespace = "mastery"

  def nextChapter = { attributes ->
    def navigation = attributes.navigation
    if (navigation.next) {
      writeLinkToListByChapter 'next', navigation.next, 0, {
        g.message(code: 'mastery.navigation.next')
      }
    }
  }

  def previousChapter = {  attributes ->
    def navigation = attributes.navigation
    if (navigation.previous) {
      writeLinkToListByChapter 'previous', navigation.previous, 0, {
        g.message(code: 'mastery.navigation.previous')
      }
    }
  }

  private def writeLinkToListByChapter(cssClass, chapter, startIndex, closure) {
    out << "<div class='${cssClass}'>"
    out << g.link(controller: 'mastery', action: 'listByChapter', id: chapter, params: [startIndex: startIndex]) {
      closure.call()
    }
    out << "</div>"
  }
}
