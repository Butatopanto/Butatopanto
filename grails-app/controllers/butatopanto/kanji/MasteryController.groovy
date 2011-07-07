package butatopanto.kanji

import grails.plugins.springsecurity.Secured

class MasteryController {

    static defaultAction = "listByChapter"
    static navigation = [title: 'Edit stories', order: 10]
    def masteryService
    def storyService
    def chapterService

    @Secured('ROLE_USER')
    def listByChapter = {
        int chapterNumber = params.int('id')
        chapterNumber = chapterNumber ?: 1
        int lastChapter = chapterService.getLastChapterNumber()
        ChapterNavigationBuilder builder = new ChapterNavigationBuilder(lastChapter)
        builder.setChapterNumber chapterNumber
        if (params.startIndex) {
            builder.setStartIndex params.int('startIndex')
        }
        builder.setFrames getMasteredFrames(chapterNumber)
        [navigation: builder.build()]
    }

    @Secured('ROLE_USER')
    def activate = {
        int from = params.int('from')
        int to = params.int('to')
        if (!from || !to) {
            flash.message = "mastery.activation.error"
            redirect(action: 'listByChapter', id: 1)
            return
        }
        masteryService.activateRange(from, to)
        def fromChapterNumber = Frame.findByNumber(from).chapter
        redirect(action: 'listByChapter', id: fromChapterNumber)
    }

    def list = {
        List shownMasteryList = listShownMastery()
        [masteryList: shownMasteryList, masteryCount: masteryService.getMasteryCount()]
    }

    private List<MasteredFrame> getMasteredFrames(int chapterNumber) {
        List<Frame> activeFrames = chapterService.listFramesFor(chapterNumber)
        toMasteredFrames(activeFrames)
    }

    private List<MasteredFrame> toMasteredFrames(List<Frame> activeFrames) {
        activeFrames.collect {
            boolean hasStory = storyService.findStoryTextByFrameId(it.id) != null
            MasteryOfFrame mastery = masteryService.findMasteryByFrameId(it.id)
            def box = mastery ? mastery.box : 0
            new MasteredFrame(frame: it, box: box, hasStory: hasStory)
        }
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
