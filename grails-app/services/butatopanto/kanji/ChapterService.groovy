package butatopanto.kanji

class ChapterService {

  static transactional = true

  List<Chapter> findAll() {
    List<Frame> frameList = Frame.list()
    Set<Integer> chapterNumbers = ((frameList.collect {it.chapter} as Set) as List).sort()
    chapterNumbers.collect {
      new Chapter(number: it, frameIds: getFramesFor(it))
    }
  }

  List<Frame> getFramesFor(def chapterNumber) {
     Frame.findAllByChapter(chapterNumber).collect {it.id}.sort()
  }

  int getLastChapterNumber() {
    def chapter = Frame.list(max: 1, sort: "chapter", order: "desc")
    chapter ? chapter[0].chapter : 0
  }
}