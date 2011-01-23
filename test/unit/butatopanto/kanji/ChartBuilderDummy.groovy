package butatopanto.kanji

class ChartBuilderDummy {

  def alternativeText
  def title
  def total
  String dataSeriesValues
  def legend
  def buildResult

  void setDataSeriesValuesForRightWrongAndRemaining(def right, def wrong, def remaining) {
    dataSeriesValues = "${right},${wrong},${remaining}"
  }

  void setDataSeriesValuesForKnownAndDueKanji(def known, def due) {
    dataSeriesValues = due.join(",")
    dataSeriesValues = dataSeriesValues.concat('|')
    dataSeriesValues = dataSeriesValues.concat(known.join(","))
  }

  private def removeLastCharacter(String string) {
    string.substring(0, string.length() - 1)
  }

  void setLegendForRightWrongAndRemaining(def right, def wrong, def remaining) {
    legend = "${right},${wrong},${remaining}"
  }

  void setLegendForKnownAndDueKanji(def known, def due) {
    legend = "${known},${due}"
  }

  def buildHtml() {
    buildResult
  }
}