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
    "<p>Gelernt: ${session.review.getReviewedCount} von ${session.review.totalCount}</p>" +
    "<p>Richtig: ${session.review.getRightCount} Falsch: ${session.review.getWrongCount}</p>"
  }

  private def renderChartProgress() {
    def total = session.review.totalCount
    def right = session.review.rightCount
    def wrong = session.review.wrongCount
    def remaining = session.review.remainingCount
    def reviewed = session.review.reviewedCount
    def alt = g.message(code: 'review.progress.alt', args: [reviewed, total, right, wrong])
    def width = "250"
    String title =  g.message(code: 'review.progress.title')
    "<img width=\"${width}\" height=\"100\" alt=\"${alt}\" src=\"http://chart.apis.google.com/chart" +
    "?chf=bg,lg,0,EFEFEF,0,D1D1D1,1" +
    "&cht=bhs" +
    "&chco=00FF00,FF0000,FFFFFF" +
    "&chxr=0,0,${total}" +
    "&chxt=x" +
    "&chs=${width}x100" +
    "&chds=0,${total},0,${total},0,${total}" +
    "&chd=t:${right}|${wrong}|${remaining}" +
    "&chtt=${title}" +
    "&chma=5,5|${width},30" +
    "&chdlp=b" +
    "&chdl=${right} richtig|${wrong} falsch|${remaining} von ${total}" +
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