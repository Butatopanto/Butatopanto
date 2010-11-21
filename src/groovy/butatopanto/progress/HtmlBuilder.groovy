package butatopanto.progress

import groovy.xml.MarkupBuilder

public abstract class HtmlBuilder {

  def buildHtml() {
    def writer = new StringWriter()
    buildMarkup(writer)
    writer.toString()
  }

  private void buildMarkup(def writer) {
    MarkupBuilder builder = new MarkupBuilder(writer)
    build(builder);
  }

  protected abstract void build(MarkupBuilder builder)
}
