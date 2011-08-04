package butatopanto.kanji

class TestChapterProgressService {

    def findAll(){
        return [new ChapterProgress(chapter: new Chapter(number: 1, frameIds: 1..15), activeFrameIds: 1..15, dueFrameIds: 1..15)]
    }
}
