package butatopanto.learning

import butatopanto.progress.CardBoxIdentifier

class FlashcardTagLib {

  static namespace = "flashcard"

  def romanNumber = {arguments ->
    out << new CardBoxIdentifier().identify(arguments.number)
  }

  def generateDataScript = {arguments ->
    def data = arguments.dataVariable
    out << "${data}.addColumn('string', 'Box');"
    out << "${data}.addColumn('number', 'Due');"
    out << "${data}.addColumn('number', 'Mastered');"
    int index = 0
    arguments.boxes.each {
      def roman = romanNumber(number: it.number)
      out << "${data}.addRow();"
      out << "${data}.setValue($index, 0, '$roman');"
      out << "${data}.setValue($index, 1, ${it.dueKanji});"
      out << "${data}.setValue($index, 2, ${it.masteredKanji});"
      index++
    }
  }
}