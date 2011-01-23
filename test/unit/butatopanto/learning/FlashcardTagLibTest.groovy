package butatopanto.learning

import butatopanto.kanji.CardBox
import butatopanto.kanji.ChartBuilderDummy
import butatopanto.sharedtest.TagLibJUnit4TestCase
import org.junit.Before
import org.junit.Test

class FlashcardTagLibTest extends TagLibJUnit4TestCase {

  ChartBuilderDummy chartBuilder = new ChartBuilderDummy()
  def boxes

  FlashcardTagLibTest() {
    super(FlashcardTagLib)
  }

  @Before
  void setChartBuilder() {
    tagLib.newChartBuilder = {
      chartBuilder
    }
  }


  @Before
  void createBoxes() {
    this.boxes = [new CardBox(number: 1, totalKanji: 15, dueKanji: 7), new CardBox(number: 2, totalKanji: 10, dueKanji: 3)]
  }

  @Test
  void createsAptlyTitledDiagram() {
    messageCodes['flashcard.chart.title'] = "ChartTitle"
    render()
    assertEquals "ChartTitle", chartBuilder.title
  }


  @Test
  void createsLegendForKnownAndDueKanji() {
    messageCodes['flashcard.chart.dueLegend'] = "dueLegend"
    messageCodes['flashcard.chart.knownLegend'] = "knownLegend"
    render()
    assertEquals "knownLegend,dueLegend", chartBuilder.legend
  }

  @Test
  void createsDataseriesForKnownAndDueKanji() {
    render()
    assertEquals "7,3|8,7", chartBuilder.dataSeriesValues
  }

  private def render() {
    tagLib.renderStatus([boxes: boxes])
  }
}