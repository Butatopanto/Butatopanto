package butatopanto.html

class MenuTagLib {

  static namespace = "menu"

  def createHomeLink = {attributes, body ->
    out << "<a class='home' href='${createLink(uri: '/')}'> "
    out << body()
    out << "</a>"
  }
}