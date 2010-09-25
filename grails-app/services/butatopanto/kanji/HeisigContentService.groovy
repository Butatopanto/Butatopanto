package butatopanto.kanji

import butatopanto.bootstrap.kanji.*

class HeisigContentService {

  def initializeDatabase() {
    log.info "Starting initialization of Heisig content."
    new Lesson1().insertFrames()
    new Lesson2().insertFrames()
    new Lesson3().insertFrames()
    new Lesson4().insertFrames()
    new Lesson5().insertFrames()
    new Lesson6().insertFrames()
    new Lesson7().insertFrames()
    log.info "Finished initialization of Heisig content."
  }
}
