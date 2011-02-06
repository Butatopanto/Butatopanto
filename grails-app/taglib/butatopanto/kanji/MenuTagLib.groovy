package butatopanto.kanji

class MenuTagLib {

  static namespace = "menu"

  def home = {
    out << "<span class='menuButton'>"
    createHomeLink([]) {
      g.message(code: 'default.home.label')
    }
    out << "</span>"
  }

  def assembleReview = {
    def linkParameters = ['class': "assemble", controller: "assembleReview"]
    writeMenuButton(linkParameters, 'menu.assembleReview')
  }

  def chapterList = {
    def linkParameters = ['class': "manage", controller: "mastery"]
    writeMenuButton(linkParameters, 'menu.kanji.overview')
  }

  def status = {
    def linkParameters = ['class': "status", controller: "flashcard"]
    writeMenuButton(linkParameters, 'menu.flashcard.status')
  }

  def backToPractice = {
    if (!session.review) {
      return
    }
    def linkParameters = ['class': "practice", controller: "review", action: "practice"]
    writeMenuButton(linkParameters, 'menu.returnToPractice')
  }

  def createHomeLink = {attributes, body ->
    out << "<a class='home' href='${createLink(uri: '/')}'> "
    out << body()
    out << "</a>"
  }

  void writeMenuButton(def linkParameters, def messageCode) {
    out << "<span class='menuButton'>"
    out << g.link(linkParameters) {
      g.message(code: messageCode)
    }
    out << "</span>"
  }
}