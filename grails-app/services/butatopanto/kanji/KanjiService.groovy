package butatopanto.kanji

class KanjiService {

  static transactional = true
  def random = new Random()

  def getRandomKanji() {
    if (Kanji.list()) {
      def index = random.nextInt(Kanji.count())
      return Kanji.list()[index]
    }
    null
  }
}
