package butatopanto.kanji

import grails.plugins.springsecurity.Secured

@Secured('ROLE_USER')
class AssembleReviewController {
    static defaultAction = "assemble"
    static navigation = [group: 'tabs', title: 'start', order: 20]

    def reviewService
    def chapterProgressService
    def masteryService

    def assemble = {
        createChapterSelection()
        showAssembleView()
    }

    def continueToAssemble = {
        showAssembleView()
    }

    def addChapter = {
        int chapterNumber = params.id.toInteger()
        def chapter = getChapterSelection(chapterNumber)
        if (!chapter.active) {
            flash.message = "review.addchapter.notactive"
            continueAssembly()
            return
        }
        chapter.selected = true
        continueAssembly()
    }

    def removeChapter = {
        int chapterNumber = params.id.toInteger()
        getChapterSelection(chapterNumber).selected = false
        continueAssembly()
    }

    def startSelectedChapters = {
        List selectedChapterNumbers = evaluateChapters().getSelectedChapterNumbers()
        session.review = reviewService.startChapters(selectedChapterNumbers)
        startPractice()
    }

    def startDueFramesFromSelectedChapter = {
        List selectedChapterNumbers = evaluateChapters().getSelectedChapterNumbers()
        session.review = reviewService.startDueFrom(selectedChapterNumbers)
        startPractice()
    }

    def startDue = {
        session.review = reviewService.startDue()
        startPractice()
    }

    def startRange = {
        int from = params.getInt('from')
        int to = params.getInt('to')
        if (!from || !to) {
            flash.message = "review.practiceRange.error"
            continueAssembly()
            return
        }
        masteryService.activateRange from, to
        session.review = reviewService.startRange(from, to)
        startPractice()
    }

    def startList = {
        def list = flash.kanji
        if (!list) {
            continueAssembly()
            return
        }
        session.review = reviewService.start(list)
        startPractice()
    }

    def practice = {
        if (!session.review) {
            redirect(action: "startDue")
        }
        else {
            redirect(controller: 'review', action: 'practice', params: [nocache: new Date().time])
        }
    }

    private def continueAssembly() {
        redirect(action: "continueToAssemble", params: [nocache: new Date().time])
    }

    private def startPractice() {
        redirect(action: "practice", params: [nocache: new Date().time])
    }

    private def getChapterSelection(int chapterNumber) {
        return evaluateChapters().getChapterForNumber(chapterNumber)
    }

    private ChapterSelectionEvaluation evaluateChapters() {
        new ChapterSelectionEvaluation(chapters: getChapters())
    }

    private def createChapterSelection() {
        session.chapters = createChapterSelections()
    }

    private List createChapterSelections() {
        def progressList = chapterProgressService.findAll()
        def chapterSelection = progressList.collect {
            transformToChapterSelection(it)
        }
        return chapterSelection
    }

    private ChapterSelection transformToChapterSelection(ChapterProgress progress) {
        def frameCount = progress.chapter.frameIds.size()
        def dueCount = progress.dueFrameIds.size()
        boolean active = progress.activeFrameIds
        def chapterNumber = progress.chapter.number
        new ChapterSelection(chapterNumber: chapterNumber, selected: false, active: active, totalFrames: frameCount, dueFrameCount: dueCount)
    }

    private def getChapters() {
        session.chapters
    }

    private def showAssembleView() {
      def chapters = evaluateChapters()
      def model = chapters.with({
            [chaptersSelected: hasChaptersSelected(), dueFrames: hasDueFrames(), dueSelected: hasDueSelected()]
        })
        render(view: 'assemble', model: model)
    }
}