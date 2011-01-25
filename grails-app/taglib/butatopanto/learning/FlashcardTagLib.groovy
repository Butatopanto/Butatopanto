package butatopanto.learning

import butatopanto.progress.CardBoxIdentifier

class FlashcardTagLib {

  static namespace = "flashcard"

  def romanNumber = {arguments ->
    out << new CardBoxIdentifier().identify(arguments.number)
  }

  def generateDataScript = {arguments ->
    def boxes = arguments.boxes
    def data = arguments.dataVariable
    def firstEntry = LeitnerService.FIRST_BOX - 1
    def lastEntry = LeitnerService.LAST_BOX - 1
    out << "${data}.addColumn('string', 'Box');"
    out << "${data}.addColumn('number', 'Due');"
    out << "${data}.addColumn('number', 'Mastered');"
    out << "${data}.addRows(${boxes.size()});"
    for (index in firstEntry..lastEntry) {
      def box = boxes[index]
      def roman = romanNumber(number: box.number)
      out << "data.setValue($index, 0, '$roman');"
      out << "data.setValue($index, 1, ${box.dueKanji});"
      out << "data.setValue($index, 2, ${box.masteredKanji});"
    }
  }
}