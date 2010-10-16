package butatopanto

import butatopanto.kanji.bean.Review
import butatopanto.progress.GoogleChartBuilder

class HeisigTagLib {

  static namespace = "heisig"

  def interaction = { attributes ->
    def frame = attributes.frame
    def hidden = attributes.hidden
    out << renderProgressBar()
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

  private def renderProgressBar() {
    "<div style='position:absolute; top: 50 px; right:7 px'>" +
    renderChartProgress(session.review) +
    "</div>"
  }

  private def renderTextualProgress(Review review) {
    "<p>Gelernt: ${review.reviewedCount} von ${review.totalCount}</p>" +
    "<p>Richtig: ${review.rightCount} Falsch: ${review.wrongCount}</p>"
  }

  private def renderChartProgress(Review review) {
    def width = "250"
    def total = review.totalCount
    def right = review.rightCount
    def wrong = review.wrongCount
    def remaining = review.remainingCount
    def reviewed = review.reviewedCount
    def alt = g.message(code: 'review.progress.alt', args: [reviewed, total, right, wrong])
    def rightLegend = g.message(code: 'review.progress.legend.right', args: [right])
    def wrongLegend = g.message(code: 'review.progress.legend.wrong', args: [wrong])
    def remainingLegend = g.message(code: 'review.progress.legend.remaining', args: [remaining, total])
    String title = g.message(code: 'review.progress.title')
    GoogleChartBuilder chartBuilder = new GoogleChartBuilder()
    chartBuilder.setTitle title
    chartBuilder.setTotal total
    chartBuilder.setAlternativeText alt
    chartBuilder.setLegendForRightWrongAndRemaining rightLegend, wrongLegend, remainingLegend
    chartBuilder.setDataSeriesValuesForRightWrongAndRemaining right, wrong, remaining
    chartBuilder.buildHtml()
  }

  private def renderRevealMessage() {
    "<p style='position:relative; top:50px'>${g.message(code: 'frame.revealMessage')}</p>"
  }

  private def renderReviewButtons(frame) {
    "<div style='position:relative; top:50px'>" +
    "<p>${g.message(code: 'frame.reviewResultQuestion')}</p>" +
    g.form(name: 'reviewKanji') {
      g.submitToRemote(update: 'container', value: g.message(code: 'frame.reviewResult.Yes'), url: [controller: 'review', action: 'ajaxResolve', params: [kanji: frame.kanji, reviewCorrect: true]]) +
      " " +
      g.submitToRemote(update: 'container', value: g.message(code: 'frame.reviewResult.No'), url: [controller: 'review', action: 'ajaxResolve', params: [kanji: frame.kanji, reviewCorrect: false]])
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
    return "<div style='width:270px; height:390px; position:relative; top:50px; background-color:white' align='center' onclick=\"${function}\"> " +
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