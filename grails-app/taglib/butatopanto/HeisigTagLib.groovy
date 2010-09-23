package butatopanto

class HeisigTagLib {

  static namespace = "heisig"

  def frameCard = { attributes ->
    def frame = attributes.frame
    def hidden = attributes.hidden
    if (hidden) {
      out << renderHiddenCard(frame);
    }
    else {
      out << renderRevealedCard(frame)
    }
  }

  def renderHiddenCard(frame) {
    def function = g.remoteFunction(action: 'reveal', update: 'container', id: frame.id)
    renderCard(function, frame.meaning, '?', '');
  }

  def renderRevealedCard(frame) {
    def function = g.remoteFunction(action: 'next', update: 'container')
    renderCard(function, frame.meaning, frame.kanji, frame.number)
  }

  private def renderCard(function, meaning, kanji, number) {
    return "<div style='width:270px; height:390px; position:relative; top:50px; background-color:white' align='center' onclick=\"${function}\"> " +
      "<table height = '100%'>" +
      renderMeaning(meaning) +
      renderCharacter(kanji) +
      renderNumber(number) +
      "</table>" +
      "</div>"
  }

  def renderNumber(number) {
    "<tr height = '10%' >" +
      "<td style = 'text-align: right; font-size:12px'>${number}</td>" +
      "</tr>"
  }

  def renderMeaning(meaning) {
    "<tr height = '10%'> " +
      "<td style = 'text-align: left; font-size:20px'>${meaning}</td>" +
      "</tr>"
  }

  def renderCharacter(character) {
    "<tr>" +
      "<td height = '100%' style = 'text-align: center; vertical-align:middle; font-size:100px' >${character}</td>" +
      "</tr>"
  }
}
