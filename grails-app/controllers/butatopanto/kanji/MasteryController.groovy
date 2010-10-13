package butatopanto.kanji

class MasteryController {

  def masteryService

  def index = {
    redirect(action: "list", params: params)
  }

  def list = {
    List shownFrameReviews = listShownMastery()
    [activeFrameReviewList: shownFrameReviews, numberOfActiveFrameReviews: masteryService.getMasteryCount()]
  }

  private List listShownMastery() {
    int max = calculateListMaxSize()
    int offset = calculateOffset()
    String sort = calculateSortAttribute()
    String order = calculateSortOrder()
    masteryService.listMastery(sort, order, offset, max)
  }

  private int calculateListMaxSize() {
    return Math.min(params.max ? params.int('max') : 20, 100)
  }

  private int calculateOffset() {
    return params.offset ? params.int('offset') : 0
  }

  private String calculateSortAttribute() {
    return params.sort ? params.sort : 'frame.number'
  }

  private String calculateSortOrder() {
    return params.order != 'desc' ? "Ascending" : "Descending"
  }
}
