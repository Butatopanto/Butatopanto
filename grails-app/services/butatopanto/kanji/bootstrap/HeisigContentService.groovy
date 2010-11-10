package butatopanto.kanji.bootstrap

class HeisigContentService {

  def initializeDatabase() {
    log.info "Starting initialization of Heisig heisig."
    def lessons = [
      new ChapterContent1(), new ChapterContent2(), new ChapterContent3(), new ChapterContent4(), new ChapterContent5(), new ChapterContent6(),
      new ChapterContent7(), new ChapterContent8(), new ChapterContent9(), new ChapterContent10(), new ChapterContent11(), new ChapterContent12(),
      new ChapterContent13(), new ChapterContent14(), new ChapterContent15()
    ]
    lessons.each { it.insertFrames() }
    log.info "Finished initialization of Heisig frames."
  }
}