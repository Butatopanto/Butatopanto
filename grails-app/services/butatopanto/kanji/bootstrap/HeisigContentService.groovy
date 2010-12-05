package butatopanto.kanji.bootstrap

class HeisigContentService {

  def missingChapterService

  def initializeDatabase() {
    log.info "Starting initialization of Heisig frames."
    def lessons = missingChapterService.missingChapters()
    lessons.each { it.insertFrames() }
    log.info "Finished initialization of Heisig frames."
  }
}