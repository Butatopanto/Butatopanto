package butatopanto.kanji

import butatopanto.kanji.MasteredFrame;

class MasteryController {

  def masteryService
  def storyService

  def listByChapter = {
    int chapterNumber = params.int('id')
    def activeFrameIds = masteryService.listActiveFrameIdsForChapter(chapterNumber)
    def activeFrames = activeFrameIds.collect {Frame.get(it)}.sort {a, b -> a.number <=> b.number}
    def masteredFrames = activeFrames.collect {
      boolean hasStory = storyService.findStoryTextByFrameId(it.id) != null
      def mastery = masteryService.findMasteryByFrameId (it.id)
      def box = mastery.box
      def kanji = it.kanji
      new MasteredFrame(kanji: kanji, box: box, hasStory: hasStory)
    }
    [chapterNumber: chapterNumber, masteredFrameList: masteredFrames]
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
