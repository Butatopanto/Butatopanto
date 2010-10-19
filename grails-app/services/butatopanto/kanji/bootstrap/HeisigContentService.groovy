package butatopanto.kanji.bootstrap

class HeisigContentService {

  def initializeDatabase() {
    log.info "Starting initialization of Heisig heisig."
    def lessons = [
      new LessonContent1(), new LessonContent2(), new LessonContent3(), new LessonContent4(), new LessonContent5(), new LessonContent6(),
      new LessonContent7(), new LessonContent8(), new LessonContent9(), new LessonContent10(), new LessonContent11(), new LessonContent12(),
      new LessonContent13()
    ]
    lessons.each { it.insertFrames() }
    log.info "Finished initialization of Heisig heisig."
  }
}