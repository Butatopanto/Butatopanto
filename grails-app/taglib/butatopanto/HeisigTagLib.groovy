package butatopanto

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
    renderChartProgress() +
    "</div>"
  }

  private def renderTextualProgress() {
    "<p>Gelernt: ${session.review.reviewedFrameCount} von ${session.review.totalFrameCount}</p>" +
    "<p>Richtig: ${session.review.correctReviewCount} Falsch: ${session.review.incorrectReviewCount}</p>"
  }

  private def renderChartProgress() {
    def alt = "Gelernt: ${session.review.reviewedFrameCount} von ${session.review.totalFrameCount}; Richtig: ${session.review.correctReviewCount} Falsch: ${session.review.incorrectReviewCount}"
    def bar = session.review.totalFrameCount
    def right = session.review.correctReviewCount
    def wrong = session.review.incorrectReviewCount
    def remaining = session.review.remainingFrameCount
    def width = "250"
    String title = "Fortschritt"
    "<img width=\"${width}\" height=\"100\" alt=\"${alt}\" src=\"http://chart.apis.google.com/chart" +
    "?chf=bg,lg,0,EFEFEF,0,D1D1D1,1" +
    "&cht=bhs" +
    "&chco=00FF00,FF0000,FFFFFF" +
    "&chxr=0,0,${bar}" +
    "&chxt=x" +
    "&chs=${width}x100" +
    "&chds=0,${bar},0,${bar},0,${bar}" +
    "&chd=t:${right}|${wrong}|${remaining}" +
    "&chtt=${title}" +
    "&chma=5,5|${width},30" +
    "&chdlp=b" +
    "&chdl=${right} richtig|${wrong} falsch|${remaining} von ${bar}" +
    "\"/>"
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