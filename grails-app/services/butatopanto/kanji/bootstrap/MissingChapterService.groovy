package butatopanto.kanji.bootstrap

class MissingChapterService {

  def allChapters() {
    new ChapterCollector().collect()
  }
}
