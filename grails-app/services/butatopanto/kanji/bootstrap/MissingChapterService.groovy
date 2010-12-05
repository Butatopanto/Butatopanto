package butatopanto.kanji.bootstrap

class MissingChapterService {

  def allChapters() {
    def allChapters = []
    int chapterNumber = 1
    while (isValidChapterNumber(chapterNumber)) {
      def chapter = loadChapter(chapterNumber)
      allChapters.add(chapter)
      chapterNumber++
    }
    return allChapters
  }

  private boolean isValidChapterNumber(int chapterNumber) {
    try {
      loadChapterClass(chapterNumber)
    } catch (Exception e) {
      return false;
    }
    return true
  }

  private def loadChapter(int chapterNumber) {
    Class<?> chapterClass = loadChapterClass(chapterNumber)
    return chapterClass.newInstance()
  }

  private Class<?> loadChapterClass(int chapterNumber) {
    String className = "butatopanto.kanji.bootstrap.ChapterContent" + chapterNumber
    getClass().getClassLoader().loadClass(className)
  }
}
