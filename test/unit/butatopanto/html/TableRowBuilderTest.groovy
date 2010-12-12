package butatopanto.html

import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test
import groovy.xml.MarkupBuilder
import groovy.util.slurpersupport.GPathResult

class TableRowBuilderTest extends GrailsJUnit4TestCase {
  private StringWriter writer = new StringWriter()
  private MarkupBuilder markupBuilder = new MarkupBuilder(writer)

  @Test
  void addsGivenIdToTableDataHtml() {
    new TableRowBuilder(id: "myId").build markupBuilder
    assertEquals "myId", getContentAsXml().td.@id.text()
  }

  @Test
  void doesNotAddIdAttributeIfNoIdIsGiven() {
    new TableRowBuilder().build markupBuilder
    def xml = new XmlSlurper().parseText(writer.toString())
    assertTrue xml.td.@id.isEmpty()
  }

  @Test
  void retainsFalseForOmitNullAttributeFlagOfMarkupBuilder() {
    markupBuilder.omitNullAttributes = false
    new TableRowBuilder().build markupBuilder
    assertFalse markupBuilder.omitNullAttributes
  }

  @Test
  void retainsTrueForOmitNullAttributeFlagOfMarkupBuilder() {
    markupBuilder.omitNullAttributes = true
    new TableRowBuilder().build markupBuilder
    assertTrue markupBuilder.omitNullAttributes
  }

  private GPathResult getContentAsXml() {
    new XmlSlurper().parseText(writer.toString())
  }
}