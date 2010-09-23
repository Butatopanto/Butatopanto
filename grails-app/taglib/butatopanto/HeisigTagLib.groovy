package butatopanto

class HeisigTagLib {

  static namespace = "heisig"

  def interaction = { attributes ->
    def frame = attributes.frame
    def hidden = attributes.hidden
    if (hidden) {
      out << renderRevealMessage()
    }
    else {
      out << renderReviewButtons(frame)
    }
  }

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

  def renderRevealMessage() {
    "<p style='position:relative; top:50px'>${g.message(code: 'frame.revealMessage')}</p>"
  }

  def renderReviewButtons(frame) {
    "<div style='position:relative; top:50px'>" +
      "<p>${g.message(code: 'frame.reviewResultQuestion')}</p>" +
      g.formRemote(name: 'reviewKanji', update: 'container', url: [controller: 'frame', action: 'reviewedCorrect', params: [kanji: frame.kanji]]) {
        "      <input class=\"reviewButton\" type=\"submit\" name=\"yes\" value=\"${g.message(code: 'frame.reviewResult.Yes')}\" id=\"reviewButton.yes\"/>"
      } +
      g.formRemote(name: 'reviewKanji', update: 'container', url: [controller: 'frame', action: 'reviewedIncorrect', params: [kanji: frame.kanji]]) {
        "      <input class=\"reviewButton\" type=\"submit\" name=\"no\" value=\"${g.message(code: 'frame.reviewResult.No')}\" id=\"reviewButton.no\"/>"
      } +
      "</div>"
  }

  def renderHiddenCard(frame) {
    def function = g.remoteFunction(action: 'reveal', update: 'container', id: frame.id)
    renderCard(function, frame.meaning, '?', '');
  }

  def renderRevealedCard(frame) {
    def function = ""
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