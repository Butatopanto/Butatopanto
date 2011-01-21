package butatopanto.kanji

import grails.plugins.springsecurity.Secured
import static butatopanto.learning.LeitnerService.FIRST_BOX
import static butatopanto.learning.LeitnerService.LAST_BOX

class FlashcardController {

  def leitnerService

  def index = {
    redirect(action: 'status')
  }

  @Secured('ROLE_USER')
  def status = {
    def boxes = [];
    for (boxnumber in FIRST_BOX..LAST_BOX) {
      def kanjiInBox = MasteryOfFrame.findAllByBox(boxnumber)
      def totalKanji = kanjiInBox.size();
      def dueKanji = kanjiInBox ? kanjiInBox.sum() {leitnerService.isDue(it) ? 1 : 0} : 0
      def box = new CardBox(number: boxnumber, totalKanji: totalKanji, dueKanji: dueKanji)
      boxes.add(box)
    }
    [boxes: boxes]
  }
}
