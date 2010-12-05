package butatopanto.kanji.bootstrap

class ChapterCollector {

  private int currentChapter = 1

  def collect() {
    def allChapters = []
    while (isValidChapter()) {
      def chapterInstance = loadCurrentChapter()
      allChapters.add(chapterInstance)
      currentChapter++
    }
    return allChapters
  }

  private boolean isValidChapter() {
    try {
      loadCurrentChapterClass()
      return true
    } catch (Exception e) {
      return false;
    }
  }

  private def loadCurrentChapter() {
    Class<?> chapterClass = loadCurrentChapterClass()
    return chapterClass.newInstance()
  }

  private Class<?> loadCurrentChapterClass() {
    String className = "butatopanto.kanji.bootstrap.ChapterContent" + currentChapter
    getClass().getClassLoader().loadClass(className)
  }
}
