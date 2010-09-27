package butatopanto

class ReviewTagLib {

  static namespace = "heisig"

  def chapter = { attributes ->
    def chapter = attributes.number
    def activeFrames = attributes.activeFrames
    def totalFrames = attributes.totalFrames
    out << "<div>"
    out << "<p style='font-size: 20px'>$chapter</p>"
    out << "<p>$activeFrames / $totalFrames</p>"
    out << "</div>"
  }
}
