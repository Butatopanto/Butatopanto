package butatopanto.kanji.bootstrap

import butatopanto.kanji.Frame

class HeisigContentService {

    def missingChapterService

    def initializeDatabase() {
        log.info "Starting initialization of Heisig frames."
        def lessons = missingChapterService.allChapters()
        lessons.each { it.insertFrames() }
        patchKeywords()
        log.info "Finished initialization of Heisig frames."
    }

    private def patchKeywords() {
        patchKeyword(723, "Abhang")
    }

    private def patchKeyword(number, keyword) {
        def frame = Frame.findByNumber(number)
        if (frame) {
            frame.setKeyword(keyword)
            frame.save(flush: true)
        }
    }

}