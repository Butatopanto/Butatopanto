package butatopanto.progress

import groovy.xml.MarkupBuilder

class GoogleChartBuilder extends HtmlBuilder {

  int width = 250
  int height = 100
  def id = 'chart'
  private def alternativeText = ""
  protected def additionalParameters = ""
  protected def type = 'bhs'

  void setAlternativeText(def text) {
    this.alternativeText = text
  }

  void setTitle(def title) {
    addParameter "chtt=${title}"
  }

  void addLegendAtBottom() {
    addParameter "chdlp=b"
  }

  void setDataSeriesColorsGreenRedAndWhite() {
    addParameter "chco=80f08e,f08080,FFFFFF"
  }

  void setDataSeriesColorsOrangeAndGreen() {
    addParameter "chco=FF9900,0FF80F"
  }

  protected void build(MarkupBuilder builder) {
    builder.img(alt: alternativeText, id: id, width: width, height: height, src: buildUrl())
  }

  protected String buildUrl() {
    "http://chart.apis.google.com/chart?chs=${width}x${height}&cht=${type}" + additionalParameters
  }

  void setTypeToBarGrid() {
    type = "bvg"
  }

  void setXAxisVisible() {
    addParameter "chxt=x"
  }

  void setXAndYAxesVisible() {
    addParameter "chxt=y,x"
  }


  void addParameter(String parameter) {
    additionalParameters += "&$parameter"
  }
}