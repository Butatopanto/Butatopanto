package butatopanto.progress

import groovy.xml.MarkupBuilder

class GoogleChartBuilder {

  private int width = 250
  private int height = 100
  private def alternativeText = "";
  private def additionalParameters = ""

  void setAlternativeText(def text) {
    this.alternativeText = text
  }

  void setTitle(def title) {
    addParameter "chtt=${title}"
  }

  void setTotal(def total) {
    setDataSeries(total)
    setAxisRange(total)
    setAxisLabels(total)
  }

  void setDataSeriesValuesForRightWrongAndRemaining(def right, def wrong, def remaining) {
    addParameter "chd=t:${right}|${wrong}|${remaining}"
  }

  void setLegendForRightWrongAndRemaining(def right, def wrong, def remaining) {
    addLegendAtBottom()
    addParameter "chdl=${right}|${wrong}|${remaining}"
  }

  def buildHtml() {
    def writer = new StringWriter()
    buildMarkup(writer)
    writer.toString()
  }

  private void addLegendAtBottom() {
    addParameter "chdlp=b"
  }

  private void setDataSeriesColorsGreenRedAndWhite() {
    addParameter "chco=80f08e,f08080,FFFFFF"
  }

  private void buildMarkup(def writer) {
    MarkupBuilder builder = new MarkupBuilder(writer)
    builder.img(alt: alternativeText, width: width, height: height, src: buildUrl())
  }

  private String buildUrl() {
    setBackgroundColorGradient()
    setXAxisVisible()
    setDataSeriesColorsGreenRedAndWhite()
    setChartMargins()
    "http://chart.apis.google.com/chart?chs=${width}x${height}&cht=bhs" + additionalParameters
  }

  private void setBackgroundColorGradient() {
    addParameter "chf=bg,lg,0,EFEFEF,0,D1D1D1,1"
  }

  private void setXAxisVisible() {
    addParameter "chxt=x"
  }

  private void setAxisRange(total) {
    addParameter "chxr=0,0,${total}"
  }

  private void setAxisLabels(total) {
    int step = total / 3
    addParameter "chxl=0:|0|${step}|${step * 2}|${total}"
  }

  private void setChartMargins() {
    addParameter "chma=5,5|${width},30"
  }

  private void setDataSeries(total) {
    addParameter "chds=0,${total},0,${total},0,${total}"
  }

  private void addParameter(String parameter) {
    additionalParameters += "&$parameter"
  }
}