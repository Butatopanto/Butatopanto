package butatopanto.progress

import groovy.xml.MarkupBuilder

class GoogleChartBuilder {

  private int width = 250
  private def alternativeText = "";

  def buildHtml() {
    def writer = new StringWriter()
    def builder = new MarkupBuilder(writer)
    buildMarkup(builder)
    writer.toString()
  }

  private void buildMarkup(MarkupBuilder builder) {
    builder.img(alt: alternativeText, width: width, height: '100', src: buildUrl())
  }

  private String buildUrl() {
    "http://chart.apis.google.com/chart?chs=250x100&cht=bhs"
  }

  void setAlternativeText(def text) {
    this.alternativeText = text
  }
}