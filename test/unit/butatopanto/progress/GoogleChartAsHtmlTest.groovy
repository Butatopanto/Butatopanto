package butatopanto.progress;


import butatopanto.test.GrailsJUnit4TestCase
import org.junit.Test
import groovy.util.slurpersupport.GPathResult

class GoogleChartAsHtmlTest extends GrailsJUnit4TestCase {

  def builder = new GoogleChartBuilder()

  @Test
  void isHtmlImage() {
    def xml = asXml()
    assertEquals "img", xml.name()
  }

  @Test
  void hasWidthAttributeWithDefaultValue() {
    def xml = asXml()
    assertEquals "250", xml.@width.text()
  }

  @Test
  void hasHeightAttributeWithDefaultValue() {
    def xml = asXml()
    assertEquals "100", xml.@height.text()
  }

  @Test
  void hasEmptyAlternativeTextByDefault() {
    def xml = asXml()
    assertEquals "", xml.@alt.text()
  }

  @Test
  void hasSpecifiedAlternativeText() {
    builder.setAlternativeText("Ich bin eine Alternative.")
    def xml = asXml()
    assertEquals "Ich bin eine Alternative.", xml.@alt.text()
  }

  @Test
  void hasEmptyBarChartInDefaultSizeAsSource() {
    def xml = asXml()
    assertEquals "http://chart.apis.google.com/chart?chs=250x100&cht=bhs", xml.@src.text()
  }

  private GPathResult asXml() {
    def text = builder.buildHtml()
    new XmlSlurper().parseText(text)
  }
}