package net.butatopanto.vocable

class VocableService {

  boolean transactional = false
  def random = new Random();

  def getRandomVocable() {
    def allVocables = allVocables()
    def vocableCount = allVocables.size()
    if (vocableCount == 0) {
      return null
    }
    def randomVocableIndex = random.nextInt(vocableCount)
    allVocables[randomVocableIndex]
  }

  def allVocables() {
    Vocable.list()
  }
}
