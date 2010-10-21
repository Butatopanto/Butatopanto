package butatopanto.kanji

class MasteryController {

  def masteryService
  def storyService
  def chapterService

  def index = {
    redirect(action: "listByChapter", id: 1)
  }

  def listByChapter = {
    int chapterNumber = params.int('id')
    List<Frame> activeFrames = chapterService.listFramesFor(chapterNumber)
    List<MasteredFrame> masteredFrames = toMasteredFrames(activeFrames)
    def previous = chapterNumber - 1
    def next = chapterNumber == chapterService.getLastChapterNumber() ? null : chapterNumber + 1
    [current: chapterNumber, masteredFrames: masteredFrames, previous: previous, next: next]
  }

  private List<MasteredFrame> toMasteredFrames(List<Frame> activeFrames) {
    activeFrames.collect {
      boolean hasStory = storyService.findStoryTextByFrameId(it.id) != null
      MasteryOfFrame mastery = masteryService.findMasteryByFrameId(it.id)
      def box = mastery ? mastery.box : 0
      new MasteredFrame(frame: it, box: box, hasStory: hasStory)
    }
  }

  def list = {
    List shownMasteryList = listShownMastery()
    [masteryList: shownMasteryList, masteryCount: masteryService.getMasteryCount()]
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
