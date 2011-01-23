package butatopanto.kanji

import grails.plugins.springsecurity.Secured
import static butatopanto.learning.LeitnerService.FIRST_BOX
import static butatopanto.learning.LeitnerService.LAST_BOX

class FlashcardController {

  def leitnerService
  def masteryQueryService

  def index = {
    redirect(action: 'status')
  }

  @Secured('ROLE_USER')
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
}
