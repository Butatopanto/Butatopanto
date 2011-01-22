package butatopanto.progress

import butatopanto.learning.LeitnerService

class FlashcardChartBuilder extends GoogleChartBuilder {

  def maximum

  FlashcardChartBuilder() {
    setWidth 600
    setHeight 400
  }

  protected String buildUrl() {
    setDataSeriesColorsOrangeAndGreen()
    setXAndYAxesVisible()
    setBoxAxisLabels()
    setKanjiAxisRange()
    "http://chart.apis.google.com/chart?chs=${width}x${height}&cht=bvg" + additionalParameters
  }

  private void setKanjiAxisRange() {
    addParameter "chxr=0,0,${maximum}"
  }

  private void setBoxAxisLabels() {
    def boxes = LeitnerService.FIRST_BOX..LeitnerService.LAST_BOX
    addParameter "chxl=1:|${boxes.join("|")}"
  }

  private void setDataSeriesColorsOrangeAndGreen() {
    addParameter "chco=FF9900,0FF80F"
  }

  void setDataSeriesValuesForKnownAndDueKanji(def known, def due) {
    def dueSeries = due.join(',')
    def knownSeries = known.join(',')
    def maxDue = due.max()
    def maxKnown = known.max()
    maximum = [maxDue, maxKnown, 100].max()
    addParameter "&chd=t:${dueSeries}|${knownSeries}"
  }

  void setLegendForKnownAndDueKanji(def known, def due) {
    addParameter "chdl=${due}|${known}"
  }
}
