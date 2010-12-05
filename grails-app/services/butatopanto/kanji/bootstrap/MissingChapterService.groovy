package butatopanto.kanji.bootstrap

import butatopanto.kanji.Frame

class MissingChapterService {

  def missingChapters() {
    def allChapters = [new ChapterContent1(), new ChapterContent2(), new ChapterContent3(), new ChapterContent4(), new ChapterContent5(), new ChapterContent6(),
            new ChapterContent7(), new ChapterContent8(), new ChapterContent9(), new ChapterContent10(), new ChapterContent11(), new ChapterContent12(),
            new ChapterContent13(), new ChapterContent14(), new ChapterContent15()]
    for (lesson in 1..15) {
      if (!Frame.findAllByChapter(lesson).isEmpty()) {
        def chapterIndex = lesson - 1
        allChapters.remove(chapterIndex)
      }
    }
    def missingChapters = allChapters
    return missingChapters
  }
}
