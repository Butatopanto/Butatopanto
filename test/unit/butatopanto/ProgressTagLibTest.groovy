package butatopanto

import butatopanto.test.TagLibJUnit4TestCase
import java.text.MessageFormat
import org.junit.Before
import org.junit.Test
import butatopanto.kanji.bean.Review
import static butatopanto.test.TagLibUtilities.*

class ProgressTagLibTest extends TagLibJUnit4TestCase {

  private ChartBuilderDummy chartBuilder = new ChartBuilderDummy()
  private Review review = new Review()

  ProgressTagLibTest() {
    super(ProgressTagLib)
  }

  @Before
  void setReviewInSession() {
    tagLib.session.review = review
  }

  @Before
  void setChartBuilder() {
    tagLib.newChartBuilder = {
      chartBuilder
    }
  }

  @Test
  void hasInternationalizedTitle() {
    messageCodes['review.progress.title'] = "ChartTitle"
    tagLib.renderProgressBar()
    assertEquals "ChartTitle", chartBuilder.title
  }

  @Test
  void hasChartBuilderContentInDiv() {
    chartBuilder.setBuildResult "my nice result"
    tagLib.renderProgressBar()
    def xml = getContentAsXml(tagLib)
    assertEquals "my nice result", xml.div[0].text()
  }
}