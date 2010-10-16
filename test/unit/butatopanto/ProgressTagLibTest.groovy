package butatopanto

import butatopanto.test.TagLibJUnit4TestCase
import java.text.MessageFormat
import org.junit.Before
import org.junit.Test
import butatopanto.kanji.bean.Review

class ProgressTagLibTest extends TagLibJUnit4TestCase {

  private def messagesByCode = [:]
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

  @Before
  void mockMessage() {
    tagLib.class.metaClass.message { arguments ->
      String message = messagesByCode[arguments.code]
      if (arguments.args) {
        message = MessageFormat.format(message, arguments.args)
      }
      return message
    }
  }

  @Test
  void hasInternationalizedTitle() {
//    messagesByCode['review.progress.title'] = "ChartTitle"
//    tagLib.renderProgressBar()
//    assertEquals "ChartTitle", chartBuilder.title
  }
}