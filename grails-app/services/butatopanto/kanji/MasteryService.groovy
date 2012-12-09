package butatopanto.kanji

import butatopanto.learning.Calendar
import butatopanto.request.ListRequester

class MasteryService {

    static transactional = true
    def userService
    def masteryQueryService
    def leitnerService
    def calendar = new Calendar()

    def activateChapter(def number) {
        def frames = Frame.findAllByChapter(number)
        activateFrames frames
    }

    def activateRange(int from, int to) {
        List frames = getAllFramesInNumberRange(from, to)
        activateFrames frames
    }

    def deactivateRange(int from, int to) {
        List frames = getAllFramesInNumberRange(from, to)
        deactivateFrames frames
    }

    private static List getAllFramesInNumberRange(int from, int to) {
        def frames = []
        for (number in from..to) {
            frames.add(Frame.findByNumber(number))
        }
        return frames
    }

    private def activateFrames(List<Frame> frames) {
        def heisigUser = findOrCreateHeisigUser()
        frames.each {
            if (!masteryQueryService.findMasteryByFrameId(it.id)) {
                heisigUser.addToMasteryList new MasteryOfFrame(frame: it, dueDate: calendar.today)
            }
        }
    }

    private def deactivateFrames(List<Frame> frames) {
        def heisigUser = findOrCreateHeisigUser()
        frames.each {
            def mastery = masteryQueryService.findMasteryByFrameId(it.id)
            if (mastery) {
                mastery.delete()
            }
        }
    }

    def listActiveFrameIdsForChapter(int chapterNumber) {
        listActiveFrameIdsForChapterList([chapterNumber])
    }

    def listDueFrameIdsForChapter(int chapterNumber) {
        List chapterList = [chapterNumber]
        listDueFramesForChapterList(chapterList)
    }

    def listDueFramesForChapterList(List chapterNumbers) {
        def masteryList = masteryQueryService.listDueMasteryForChapterList(chapterNumbers)
        masteryList.collect {it.frame.id}
    }

    def listDueFrameIds() {
        def masteryList = masteryQueryService.listDueMastery()
        masteryList.collect {it.frame.id}
    }

    def listActiveFrameIdsForChapterList(List chapterNumbers) {
        def masteryList = masteryQueryService.listMasteryForChapterList(chapterNumbers)
        masteryList.collect {it.frame.id}
    }

    def listActiveFrameIds() {
        def masteryList = listMastery()
        masteryList.collect {it.frame.id}
    }

    List listMastery(String sortAttribute, String order, int offset, int max) {
        List masteryList = listMastery()
        ListRequester.From(masteryList)."sorted${order}By"(sortAttribute).startingFromIndex(offset).getAtMostElements(max)
    }

    def listMastery() {
        masteryQueryService.listMasteryForCurrentUser()
    }

    def answerRight(def frameId) {
        def mastery = findMasteryByFrameId(frameId)
        mastery.passed += 1
        leitnerService.answeredRight(mastery)
        mastery.save()
    }

    def answerWrong(def frameId) {
        def mastery = findMasteryByFrameId(frameId)
        mastery.failed += 1
        leitnerService.answeredWrong(mastery)
        mastery.save()
    }

    MasteryOfFrame findMasteryByFrameId(long frameId) {
        masteryQueryService.findMasteryByFrameId(frameId)
    }

    int getMasteryCount() {
        listMastery().size()
    }

    int getHindmostMasteredChapter() {
        List userMastery = listMastery()
        if (!userMastery) {
            return 1;
        }
        userMastery.sort {
            it.frame.number
        }
        userMastery.last().frame.chapter
    }

    private def findOrCreateHeisigUser() {
        userService.findOrCreateHeisigUser()
    }
}
