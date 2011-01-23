package butatopanto.learning

import butatopanto.progress.FlashcardChartBuilder

class FlashcardTagLib {

  static namespace = "flashcard"

  def renderStatus = {  arguments ->
    out << "<div>"
    out << renderChart(arguments.boxes)
    out << "</div>"
  }

  private def renderChart(def boxes) {
    def chartBuilder = newChartBuilder.call();
    chartBuilder.setTitle title
    chartBuilder.setLegendForKnownAndDueKanji knownLegend, dueLegend
    def known = getKnownKanji(boxes)
    def due = getDueKanji(boxes)
    chartBuilder.setDataSeriesValuesForKnownAndDueKanji known, due
    chartBuilder.buildHtml()
  }

  def getKnownKanji(def boxes) {
    boxes.collect {it.totalKanji - it.dueKanji}
  }

  def getDueKanji(def boxes) {
    boxes.collect {it.dueKanji}
  }

  protected def newChartBuilder = {
    new FlashcardChartBuilder()
  }

  private def getTitle() {
    g.message(code: 'flashcard.chart.title')
  }

  private def getKnownLegend() {
    g.message(code: 'flashcard.chart.knownLegend')
  }

  private def getDueLegend() {
    g.message(code: 'flashcard.chart.dueLegend')
  }
}