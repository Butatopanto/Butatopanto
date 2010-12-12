package butatopanto.kanji

import butatopanto.html.TableRowBuilder

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

  def storyDialog = { attributes ->
    if (session.review) {
      out << "<div style='position: absolute; top: 50px; left: 15px'>"
      out << "<div id='showStory' onclick='openStoryDialog(this, 'currentStory')'><b><br>Hier klicken,<br>um die Geschichte anzuzeigen</b></div>"
      out << "</div>"
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
    renderRow(keyword, 'frameCardKeyword', 10)
  }

  private def renderKanji(character) {
    renderRow(character, 'frameCardKanji', 100, "kanji-card")
  }

  private def renderNumber(number) {
    renderRow(number, 'frameCardNumber', 10)
  }

  private def renderRow(def content, def classname, int height, def id = null) {
    def builder = new TableRowBuilder()
    builder.setClassname classname
    builder.setHeightInPercent height
    builder.setContent content
    builder.setId id
    builder.buildHtml()
  }
}