package butatopanto.kanji

class HeisigTagLib {

  static namespace = "heisig"

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
    "<p class='frameMessage'>${g.message(code: 'frame.revealMessage')}</p>"
  }

  private def renderReviewButtons(frame) {
    "<div class='frameMessage'>" +
    "<p>${g.message(code: 'frame.reviewResultQuestion')}</p>" +
    g.form(name: 'reviewKanji') {
      g.submitToRemote(onComplete: 'closeStoryDialog()', id: 'confirmButton', class: "confirm", update: 'container', value: g.message(code: 'frame.reviewResult.confirm'), url: [controller: 'review', action: 'ajaxResolve', params: [kanji: frame.kanji, reviewCorrect: true]]) +
      " " +
      g.submitToRemote(onComplete: 'closeStoryDialog()', id: 'declineButton', class: "decline", update: 'container', value: g.message(code: 'frame.reviewResult.decline'), url: [controller: 'review', action: 'ajaxResolve', params: [kanji: frame.kanji, reviewCorrect: false]])
    } +
    "</div>"
  }

  private def renderHiddenCard(frame) {
    def function = g.remoteFunction(action: 'ajaxReveal', update: 'container', id: frame.id)
    renderCard(function, frame.keyword, '?', '');
  }

  private def renderRevealedCard(frame) {
    def function = ""
    renderCard(function, frame.keyword, frame.kanji, frame.number)
  }

  private def renderCard(function, meaning, kanji, number) {
    return "<div id='card' class='frameCard' onclick=\"${function}\"> " +
           "<table height = '100%'>" +
           renderKeyword(meaning) +
           renderKanji(kanji) +
           renderNumber(number) +
           "</table>" +
           "</div>"
  }

  private def renderKeyword(keyword) {
    "<tr height = '10%'><td class='frameCardKeyword'>${keyword}</td></tr>"
  }

  private def renderKanji(character) {
    "<tr><td class='frameCardKanji'>${character}</td></tr>"
  }

  private def renderNumber(number) {
    "<tr height = '10%' ><td class='frameCardNumber'>${number}</td></tr>"
  }
}