package butatopanto.kanji

import butatopanto.progress.HtmlBuilder
import groovy.xml.MarkupBuilder

public class TableRowBuilder extends HtmlBuilder {

  int heightInPercent = 100
  def classname
  def content

  protected void build(MarkupBuilder builder) {
    builder.tr(height: "$heightInPercent%") {
      td(class: classname, content)
    }
  }
}