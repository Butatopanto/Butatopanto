package butatopanto.kanji

import grails.plugins.springsecurity.Secured
import static butatopanto.learning.LeitnerService.FIRST_BOX
import static butatopanto.learning.LeitnerService.LAST_BOX

@Secured('ROLE_USER')
class FlashcardController {

  def leitnerService
  def masteryQueryService

  def index = {
    redirect(action: 'status')
  }

  def status = {
    def boxes = [];
    for (boxnumber in FIRST_BOX..LAST_BOX) {
      def kanjiInBox = masteryQueryService.listMasteriesForBox(boxnumber)
      def dueKanji = kanjiInBox ? kanjiInBox.sum() {leitnerService.isDue(it) ? 1 : 0} : 0
      def masteredKanji = kanjiInBox.size() - dueKanji;
      def daysUntilDue = leitnerService.getExpirationIntervalForBox(boxnumber)
      def box = new CardBox(number: boxnumber, daysUntilDue: daysUntilDue, masteredKanji: masteredKanji, dueKanji: dueKanji)
      boxes.add(box)
    }
    [boxes: boxes]
  }

  def startBox = {
    int boxnumber = params.id.toInteger()
    log.info boxnumber
    def kanjiInBox = masteryQueryService.listMasteriesForBox(boxnumber)
    def dueKanjiIds = kanjiInBox.findAll({leitnerService.isDue it}).collect {it.frame.number}
    if (dueKanjiIds) {
      flash.kanji = dueKanjiIds
      redirect(controller: 'assembleReview', action: 'startList')
    }
    else {
      redirect(action: 'status')
    }
  }
}