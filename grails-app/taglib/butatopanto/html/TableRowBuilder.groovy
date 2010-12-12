package butatopanto.html

import butatopanto.progress.HtmlBuilder
import groovy.xml.MarkupBuilder

public class TableRowBuilder extends HtmlBuilder {

  int heightInPercent = 100
  def classname
  def content
  def id

  protected void build(MarkupBuilder builder) {
    boolean originalOmitNullAttributes = builder.omitNullAttributes
    builder.omitNullAttributes = true
    buildMarkup(builder)
    builder.omitNullAttributes = originalOmitNullAttributes
  }

  private def buildMarkup(MarkupBuilder builder) {
    builder.tr(height: "$heightInPercent%") {
      td(id: id, class: classname, content)
    }
  }
}