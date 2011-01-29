package butatopanto.learning

import butatopanto.progress.CardBoxIdentifier

class FlashcardTagLib {

  static namespace = "flashcard"

  def romanNumber = {arguments ->
    out << new CardBoxIdentifier().identify(arguments.number)
  }

  def generateDataScript = {arguments ->
    def data = arguments.dataVariable
    def due = g.message(code: "flashcard.chart.dueLegend")
    def mastered = g.message(code: "flashcard.chart.knownLegend")
    out << "${data}.addColumn('string');"
    out << "${data}.addColumn('number', '${due}');"
    out << "${data}.addColumn('number', '${mastered}');"
    arguments.boxes.each {
      def roman = romanNumber(number: it.number)
      out << "${data}.addRow(['$roman', ${it.dueKanji}, ${it.masteredKanji}]);"
    }
  }
}