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

  def previousChapter = { attributes ->
    def navigation = attributes.navigation
    if (navigation.previous) {
      writeLinkToListByChapter 'previous', navigation.previous, 0, {
        g.message(code: 'mastery.navigation.previous')
      }
    }
  }

  def overrun = { attributes ->
    def navigation = attributes.navigation
    if (navigation.isOverrun()) {
      writeLinkToListByChapter('overrun', navigation.chapterNumber, navigation.startIndex + 10) {
        out << "<img src='../../images/skin/arrow-down.png'/>"
      }
    }
  }

    def underrun = { attributes ->
      def navigation = attributes.navigation
      if (navigation.isUnderrun()) {
        writeLinkToListByChapter('underrun', navigation.chapterNumber, navigation.startIndex - 10) {
          out << "<img src='../../images/skin/arrow-up.png'/>"
         }
      }
    }

  private def writeLinkToListByChapter(cssClass, chapter, startIndex, closure) {
    out << "<div class='${cssClass}'>"
    out << g.link(controller: 'mastery', action: 'listByChapter', id: chapter, elementId: cssClass, params: [startIndex: startIndex]) {
      closure.call()
    }
    out << "</div>"
  }
}
