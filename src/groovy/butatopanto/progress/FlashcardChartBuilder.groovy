package butatopanto.progress

import butatopanto.learning.LeitnerService

class FlashcardChartBuilder extends GoogleChartBuilder {

  private def maximumValueOnYAxis = 100

  FlashcardChartBuilder() {
    setWidth 750
    setHeight 400
    setId 'flashcard-chart'
  }

  void setDataSeriesValuesForKnownAndDueKanji(def known, def due) {
    def dueSeries = due.join(',')
    def knownSeries = known.join(',')
    def maxDue = due.max()
    def maxKnown = known.max()
    maximumValueOnYAxis = [maxDue, maxKnown, 100].max()
    addParameter "&chd=t:${dueSeries}|${knownSeries}"
  }

  void setLegendForKnownAndDueKanji(def known, def due) {
    addParameter "chdl=${due}|${known}"
  }

  protected String buildUrl() {
    setDataSeriesColorsOrangeAndGreen()
    setXAndYAxesVisible()
    setBoxAxisLabels()
    setKanjiAxisRange()
    setTypeToBarGrid()
    addLegendAtBottom()
    super.buildUrl()
  }

  private void setKanjiAxisRange() {
    addParameter "chxr=0,0,${maximumValueOnYAxis}"
  }

  private void setBoxAxisLabels() {
    def boxes = LeitnerService.FIRST_BOX..LeitnerService.LAST_BOX
    def romans = boxes.collect {new CardBoxIdentifier().identify(it)}
    addParameter "chxl=1:|${romans.join("|")}"
  }
}
