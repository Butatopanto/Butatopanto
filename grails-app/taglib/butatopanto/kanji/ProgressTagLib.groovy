package butatopanto.kanji

import butatopanto.learning.Review
import butatopanto.progress.GoogleChartBuilder
import butatopanto.progress.ProgressChartBuilder

class ProgressTagLib {

  static namespace = "progress"

  def renderProgressBar = {
    out << "<div class='yui3-u-1-3' align='center' style='margin-top: 50px;'>"
    out << renderChart()
    out << "</div>"
  }

  private def renderChart() {
    def chartBuilder = newChartBuilder.call()
    chartBuilder.setTitle title
    chartBuilder.setTotal totalCount
    chartBuilder.setAlternativeText alternativeText
    chartBuilder.setLegendForRightWrongAndRemaining rightLegend, wrongLegend, remainingLegend
    chartBuilder.setDataSeriesValuesForRightWrongAndRemaining rightCount, wrongCount, remainingCount
    chartBuilder.buildHtml()
  }

  protected def newChartBuilder = {
    new ProgressChartBuilder()
  }

  private def getTitle() {
    g.message(code: 'review.progress.title')
  }

  private def getRemainingLegend() {
    g.message(code: 'review.progress.legend.remaining', args: [remainingCount, totalCount])
  }

  private def getWrongLegend() {
    g.message(code: 'review.progress.legend.wrong', args: [wrongCount])
  }

  private def getRightLegend() {
    g.message(code: 'review.progress.legend.right', args: [rightCount])
  }

  private def getAlternativeText() {
    g.message(code: 'review.progress.alternativeText', args: [reviewedCount, totalCount, rightCount, wrongCount])
  }

  private def getTotalCount() {
    review.totalCount
  }

  private def getRemainingCount() {
    review.remainingCount
  }

  private def getReviewedCount() {
    review.reviewedCount
  }

  private def getRightCount() {
    review.rightCount
  }

  private def getWrongCount() {
    review.wrongCount
  }

  private Review getReview() {
    session.review
  }
}
