package net.butatopanto.vocable

class VocableService {

  boolean transactional = false
  def random = new Random();

  def getRandomVocable() {
    def allVocables = getAllVocables()
    def vocableCount = allVocables.size()
    def randomVocableIndex = random.nextInt(vocableCount)
    allVocables[randomVocableIndex]
  }

  def getAllVocables() {
    Vocable.list()
  }
}
