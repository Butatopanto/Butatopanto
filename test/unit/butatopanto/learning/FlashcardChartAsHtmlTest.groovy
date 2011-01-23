package butatopanto.learning

import butatopanto.progress.FlashcardChartBuilder
import butatopanto.sharedtest.GrailsJUnit4TestCase
import groovy.util.slurpersupport.GPathResult
import org.junit.Test

class FlashcardChartAsHtmlTest extends GrailsJUnit4TestCase {

  def builder = new FlashcardChartBuilder()

  @Test
  void isHtmlImage() {
    def xml = asXml()
    assertEquals "img", xml.name()
  }

  @Test
  void hasWidthAttributeWithDefaultValue() {
    assertHasWidthValue "600"
  }

  @Test
  void hasHeightAttributeWithDefaultValue() {
    assertHasHeightValue "400"
  }

  @Test
  void hasDataSeriesColorsOrangeAndGreen() {
    assertUrlContains "&chco=FF9900,0FF80F"
  }

  @Test
  void hasEmptyAlternativeTextByDefault() {
    assertHasAltValue ''
  }

  @Test
  void hasVerticalBarGridInDefaultSizeAsSource() {
    assertUrlContains "http://chart.apis.google.com/chart?chs=600x400&cht=bvg"
  }

  @Test
  void addsTitleToChartUrl() {
    builder.setTitle "Super-Chart"
    assertUrlContains "&chtt=Super-Chart"
  }

  @Test
  void setsDataSeriesValuesForKnownAndDueKanji() {
    builder.setDataSeriesValuesForKnownAndDueKanji([11, 12], [1, 2])
    assertUrlContains "&chd=t:1,2|11,12"
  }

  @Test
  void setsLegendForKnownAndDueKanji() {
    builder.setLegendForKnownAndDueKanji("Known", "Due")
    assertUrlContains "&chdl=Due|Known"
  }

  @Test
  void showsXAndYAxis() {
    assertUrlContains "chxt=y,x"
  }

  @Test
  void showsBoxLabelsForXAxis() {
    assertUrlContains "chxl=1:|1|2|3|4|5|6|7|8"
  }

  @Test
  void hasMaximumKanjiCountAsMaximumValueForYAxis() {
    builder.setDataSeriesValuesForKnownAndDueKanji([200], [1])
    assertUrlContains "chxr=0,0,200"
  }

  @Test
  void hasMaximumKanjiCountOf100IfActualMaximumIsSmaller() {
    builder.setDataSeriesValuesForKnownAndDueKanji([12], [1])
    assertUrlContains "chxr=0,0,100"
  }

  private def assertUrlContains(String substring) {
    def xml = asXml()
    assertTrue "Got url: ${xml.@src.text()}", xml.@src.text().contains(substring)
  }

  private GPathResult asXml() {
    def text = builder.buildHtml()
    new XmlSlurper().parseText(text)
  }

  def invokeMethod(String name, args) {
    def matcher = name =~ /assertHas(.*)Value/
    def attributeName = matcher[0][1].toLowerCase()
    assertHasAttributeValue attributeName, args[0]
  }

  private void assertHasAttributeValue(def attribute, def value) {
    def xml = asXml()
    assertEquals value, xml.@"$attribute".text()
  }
}
