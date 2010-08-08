package butatopanto.vocable

class VocableService {

  boolean transactional = false
  def random = new Random();

  def getRandomVocable() {
    def allVocables = Vocable.list()
    getRandomVocableFromSelection(allVocables)
  }

  def getRandomVocable(def studylist) {
    def vocablesFromList = studylist.vocables
    getRandomVocableFromSelection(vocablesFromList)
  }

  private def getRandomVocableFromSelection(def vocableSelection) {
    if (!vocableSelection) {
      return null
    }
    def vocableCount = vocableSelection.size()
    def randomVocableIndex = random.nextInt(vocableCount)
    def vocableList = vocableSelection as List
    vocableList[randomVocableIndex]
  }
}
