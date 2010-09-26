package butatopanto.kanji.bootstrap

class HeisigContentService {

  def initializeDatabase() {
    log.info "Starting initialization of Heisig heisig."
    def lessons = [new Lesson1(), new Lesson2(), new Lesson3(), new Lesson4(), new Lesson5(), new Lesson6(), new Lesson7()]
    lessons.each { it.insertFrames() }
    log.info "Finished initialization of Heisig heisig."
  }
}