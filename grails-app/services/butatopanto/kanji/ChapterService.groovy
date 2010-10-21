package butatopanto.kanji

class ChapterService {

  static transactional = true

  List<Chapter> findAll() {
    List<Frame> frameList = Frame.list()
    Set<Integer> chapterNumbers = ((frameList.collect {it.chapter} as Set) as List).sort()
    chapterNumbers.collect {
      new Chapter(number: it, frameIds: listFramesFor(it).collect {it.id})
    }
  }

  List<Frame> listFramesFor(def chapterNumber) {
    Frame.findAllByChapter chapterNumber
  }

  int getLastChapterNumber() {
    def chapter = Frame.list(max: 1, sort: "chapter", order: "desc")
    chapter ? chapter[0].chapter : 0
  }
}