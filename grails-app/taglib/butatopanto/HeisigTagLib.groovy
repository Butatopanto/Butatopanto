package butatopanto

class HeisigTagLib {

  static namespace = "heisig"

  def frameCard = { attributes ->
    def frame = attributes.frame
    out << renderCard(frame)
  }

  def renderCard(frame) {
    def function = 'reveal("' + frame?.kanji + '")'
    "<div style='width:270px; height:390px ;position:relative; top:50px; background-color:white;valign:middle' align='center' onclick='" + function + "'> " +
            renderContent(frame) +
            "</div>"
  }

  def renderContent(frame) {
    "<table height = '100%'>" +
            renderMeaning(frame.meaning) +
            renderCharacter() +
            renderNumber(frame.number) +
            "</table>"
  }

  def renderNumber(number) {
    "<tr height = '10%' >" +
            "<td id = 'number' style = 'text-align: right; visibility: hidden; font-size:12px'>${number}</td>" +
            "</tr>"
  }

  def renderMeaning(meaning) {
    "<tr height = '10%'> " +
            "<td style = 'text-align: left; font-size:20px'>${meaning}</td>" +
            "</tr>"
  }

  def renderCharacter() {
    "<tr>" +
            "<td id = 'character' height = '100%' style = 'text-align: center; vertical-align:middle; font-size:100px' > ? </td>" +
            "</tr>"
  }
}
