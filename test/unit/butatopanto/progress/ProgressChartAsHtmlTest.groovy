package butatopanto.progress;


import butatopanto.sharedtest.GrailsJUnit4TestCase
import groovy.util.slurpersupport.GPathResult
import org.junit.Test

class ProgressChartAsHtmlTest extends GrailsJUnit4TestCase {

  def builder = new ProgressChartBuilder()

  @Test
  void isHtmlImage() {
    def xml = asXml()
    assertEquals "img", xml.name()
  }

  @Test
  void hasWidthAttributeWithDefaultValue() {
    assertHasWidthValue "250"
  }

  @Test
  void hasHeightAttributeWithDefaultValue() {
    assertHasHeightValue "100"
  }

  @Test
  void hasColorGradientInBackground() {
    assertUrlContains "&chf=bg,lg,0,EFEFEF,0,D1D1D1,1"
  }

  @Test
  void hasDataSeriesColorsGreenRedAndWhite() {
    assertUrlContains "&chco=80f08e,f08080,FFFFFF"
  }

  @Test
  void hasXAxisAsOnlyVisibleAxis() {
    assertUrlContains "&chxt=x&"
  }

  @Test
  void hasChartMarginsHonoringDefaultWidth() {
    assertUrlContains '&chma=5,5|250,30'
  }

  @Test
  void hasEmptyAlternativeTextByDefault() {
    assertHasAltValue ''
  }

  @Test
  void hasSpecifiedAlternativeText() {
    builder.setAlternativeText 'Ich bin eine Alternative.'
    assertHasAltValue 'Ich bin eine Alternative.'
  }

  @Test
  void hasEmptyBarChartInDefaultSizeAsSource() {
    assertUrlContains "http://chart.apis.google.com/chart?chs=250x100&cht=bhs"
  }

  @Test
  void addsTitleToChartUrl() {
    builder.setTitle "Super-Chart"
    assertUrlContains "&chtt=Super-Chart"
  }

  @Test
  void setsThreeDataSeriesEntriesForTotal() {
    builder.setTotal 12
    assertUrlContains "&chds=0,12,0,12,0,12"
  }

  @Test
  void setsAxisRangeForTotal() {
    builder.setTotal 13
    assertUrlContains "&chxr=0,0,13"
  }

  @Test
  void setsFourAxisLabelsForTotal() {
    builder.setTotal 15
    assertUrlContains "&chxl=0:|0|5|10|15"
  }

  @Test
  void addsMultipleQualitiesToUrl() {
    builder.setTitle "Super-Chart"
    builder.setDataSeriesValuesForRightWrongAndRemaining 11, 1, 14
    assertUrlContains "&chtt=Super-Chart&chd=t:11|1|14"
  }

  @Test
  void setsDataSeriesValuesForRightWrongAndRemaining() {
    builder.setDataSeriesValuesForRightWrongAndRemaining 11, 1, 14
    assertUrlContains "&chd=t:11|1|14"
  }

  @Test
  void setsLegendAtBottomWithRightWrongAndRemainingLabel() {
    builder.setLegendForRightWrongAndRemaining("right", "wrong", "remaining")
    assertUrlContains "&chdlp=b&chdl=right|wrong|remaining"
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