package butatopanto.kanji

class MenuTagLib {

  static namespace = "menu"

  def home = {
    out << "<span class='menuButton'>"
    out << "<a class='home' href='${createLink(uri: '/')}'> "
    out << g.message(code: 'default.home.label')
    out << "</a>"
    out << "</span>"
  }

  def assembleReview = {
    def linkParameters = ['class': "assemble", controller: "review", action: "assemble"]
    writeMenuButton(linkParameters, 'menu.assembleReview')
  }

  def chapterList = {
    def linkParameters = ['class': "manage", controller: "mastery", action: "listByChapter", id: 1]
    writeMenuButton(linkParameters, 'menu.kanji.overview')
  }

  def backToPractice = {
    if (!session.review) {
      return
    }
    def linkParameters = ['class': "practice", controller: "review", action: "practice"]
    writeMenuButton(linkParameters, 'menu.returnToPractice')
  }

  void writeMenuButton(def linkParameters, def messageCode) {
    out << "<span class='menuButton'>"
    out << g.link(linkParameters) {
      g.message(code: messageCode)
    }
    out << "</span>"
  }
}