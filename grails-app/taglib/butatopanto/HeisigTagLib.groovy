package butatopanto

class HeisigTagLib {

  static namespace = "heisig"

  def story = { attributes ->
    def text = attributes.text ?: g.message(code: 'review.story.unknown')
    out << "<p>${text}</p>"
  }

  def interaction = { attributes ->
    def frame = attributes.frame
    def hidden = attributes.hidden
    out << progress.renderProgressBar()
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

  private def renderRevealMessage() {
    "<p style='position:relative; top:50px'>${g.message(code: 'frame.revealMessage')}</p>"
  }

  private def renderReviewButtons(frame) {
    "<div style='position:relative; top:50px'>" +
    "<p>${g.message(code: 'frame.reviewResultQuestion')}</p>" +
    g.form(name: 'reviewKanji') {
      g.submitToRemote(id:'confirmButton', class:"confirm", update: 'container', value: g.message(code: 'frame.reviewResult.confirm'), url: [controller: 'review', action: 'ajaxResolve', params: [kanji: frame.kanji, reviewCorrect: true]]) +
      " " +
      g.submitToRemote(id:'declineButton', class:"decline", update: 'container', value: g.message(code: 'frame.reviewResult.decline'), url: [controller: 'review', action: 'ajaxResolve', params: [kanji: frame.kanji, reviewCorrect: false]])
    } +
    "</div>"
  }

  private def renderHiddenCard(frame) {
    def function = g.remoteFunction(action: 'ajaxReveal', update: 'container', id: frame.id)
    renderCard(function, frame.meaning, '?', '');
  }

  private def renderRevealedCard(frame) {
    def function = ""
    renderCard(function, frame.meaning, frame.kanji, frame.number)
  }

  private def renderCard(function, meaning, kanji, number) {
    return "<div id='card' style='width:270px; height:390px; position:relative; top:50px; background-color:white' align='center' onclick=\"${function}\"> " +
           "<table height = '100%'>" +
           renderMeaning(meaning) +
           renderCharacter(kanji) +
           renderNumber(number) +
           "</table>" +
           "</div>"
  }

  private def renderNumber(number) {
    "<tr height = '10%' >" +
    "<td style = 'text-align: right; font-size:12px'>${number}</td>" +
    "</tr>"
  }

  private def renderMeaning(meaning) {
    "<tr height = '10%'> " +
    "<td style = 'text-align: left; font-size:20px'>${meaning}</td>" +
    "</tr>"
  }

  private def renderCharacter(character) {
    "<tr>" +
    "<td height = '100%' style = 'text-align: center; vertical-align:middle; font-size:100px' >${character}</td>" +
    "</tr>"
  }
}