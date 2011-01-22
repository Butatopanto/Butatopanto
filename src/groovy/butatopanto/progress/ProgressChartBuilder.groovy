package butatopanto.progress

class ProgressChartBuilder extends GoogleChartBuilder {

  ProgressChartBuilder(){
    setId 'progress-chart'
  }

  void setDataSeriesValuesForRightWrongAndRemaining(def right, def wrong, def remaining) {
    addParameter "chd=t:${right}|${wrong}|${remaining}"
  }

  void setLegendForRightWrongAndRemaining(def right, def wrong, def remaining) {
    addLegendAtBottom()
    addParameter "chdl=${right}|${wrong}|${remaining}"
  }

  void setTotal(def total) {
    setDataSeries(total)
    setAxisRange(total)
    setAxisLabels(total)
  }

  protected String buildUrl() {
    setBackgroundColorGradient()
    setXAxisVisible()
    setDataSeriesColorsGreenRedAndWhite()
    setChartMargins()
    super.buildUrl()
  }

  private void setAxisRange(total) {
    addParameter "chxr=0,0,${total}"
  }

  private void setAxisLabels(total) {
    int step = total / 3
    addParameter "chxl=0:|0|${step}|${step * 2}|${total}"
  }

  private void setDataSeries(total) {
    addParameter "chds=0,${total},0,${total},0,${total}"
  }

  private void setBackgroundColorGradient() {
    addParameter "chf=bg,lg,0,EFEFEF,0,D1D1D1,1"
  }

  private void setChartMargins() {
    addParameter "chma=5,5|${width},30"
  }
}
