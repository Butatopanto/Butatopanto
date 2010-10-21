package butatopanto.kanji

class MasteryController {

  def masteryService
  def storyService
  def chapterService

  def listByChapter = {
    int chapterNumber = params.int('id')
    def activeFrameIds = chapterService.getFramesFor(chapterNumber)
    List masteredFrames = asMasteredFrames(activeFrameIds)
    def previous = chapterNumber - 1
    def next = chapterNumber == chapterService.getLastChapterNumber() ? null : chapterNumber + 1
    [current: chapterNumber, masteredFrames: masteredFrames, previous: previous, next: next]
  }

  private List asMasteredFrames(List activeFrameIds) {
    activeFrameIds.collect {
      Frame frame = Frame.get(it)
      boolean hasStory = storyService.findStoryTextByFrameId(it) != null
      MasteryOfFrame mastery = masteryService.findMasteryByFrameId(frame.id)
      def box = mastery ? mastery.box : 0
      new MasteredFrame(keyword: frame.keyword, kanji: frame.kanji, box: box, hasStory: hasStory)
    }
  }

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
