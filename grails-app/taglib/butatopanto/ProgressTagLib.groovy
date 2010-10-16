package butatopanto

import butatopanto.learning.Review
import butatopanto.progress.GoogleChartBuilder

class ProgressTagLib {

  static namespace = "progress"

  def renderProgressBar = {
    out << "<div style='position:absolute; top: 50 px; right:7 px'>"
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
    new GoogleChartBuilder()
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
