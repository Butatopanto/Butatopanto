package butatopanto.kanji

class ChartBuilderDummy {

  def alternativeText
  def title
  def total
  def dataSeriesValues
  def legend
  def buildResult

  void setDataSeriesValuesForRightWrongAndRemaining(def right, def wrong, def remaining) {
    dataSeriesValues = "${right},${wrong},${remaining}"
  }

  void setLegendForRightWrongAndRemaining(def right, def wrong, def remaining) {
    legend = "${right},${wrong},${remaining}"
  }

  def buildHtml() {
    buildResult
  }
}