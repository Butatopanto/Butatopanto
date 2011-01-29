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
    arguments.boxes.each {
      def roman = romanNumber(number: it.number)
      out << "${data}.addRow(['$roman', ${it.dueKanji}, ${it.masteredKanji}]);"
    }
  }
}