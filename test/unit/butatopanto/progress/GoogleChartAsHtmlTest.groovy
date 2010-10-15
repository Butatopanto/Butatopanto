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
    assertHasAttributeValue("width", "250")
  }

  @Test
  void hasHeightAttributeWithDefaultValue() {
    assertHasAttributeValue("height", "100")
  }

  @Test
  void hasEmptyAlternativeTextByDefault() {
    assertHasAttributeValue("alt", "")
  }

  @Test
  void hasSpecifiedAlternativeText() {
    builder.setAlternativeText("Ich bin eine Alternative.")
    assertHasAttributeValue("alt", "Ich bin eine Alternative.")
  }

  @Test
  void hasEmptyBarChartInDefaultSizeAsSource() {
    assertHasAttributeValue("src", "http://chart.apis.google.com/chart?chs=250x100&cht=bhs")
  }

  @Test
  void addsTitleToChartUrl() {
    builder.setTitle("Super-Chart")
    assertUrlEndsWith("&chtt=Super-Chart")
  }

  @Test
  void addsLegendAtBottom() {
    builder.addLegendAtBottom()
    assertUrlEndsWith("&chdlp=b")
  }

  @Test
  void addsMultipleQualitiesToUrl() {
    builder.setTitle("Super-Chart")
    builder.addLegendAtBottom()
    assertUrlEndsWith("&chtt=Super-Chart&chdlp=b")
  }

  private void assertHasAttributeValue(def attribute, def value) {
    def xml = asXml()
    assertEquals value, xml.@"$attribute".text()
  }

  private def assertUrlEndsWith(String end) {
    def xml = asXml()
    assertTrue "Got url: ${xml.@src.text()}", xml.@src.text().endsWith(end)
  }

  private GPathResult asXml() {
    def text = builder.buildHtml()
    new XmlSlurper().parseText(text)
  }
}