package butatopanto.progress

class ProgressChartBuilder extends GoogleChartBuilder {

  void setDataSeriesValuesForRightWrongAndRemaining(def right, def wrong, def remaining) {
    addParameter "chd=t:${right}|${wrong}|${remaining}"
  }

  void setLegendForRightWrongAndRemaining(def right, def wrong, def remaining) {
    addLegendAtBottom()
    addParameter "chdl=${right}|${wrong}|${remaining}"
  }
}
