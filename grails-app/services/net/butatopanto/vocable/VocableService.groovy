package net.butatopanto.vocable

class VocableService {

  boolean transactional = false
  def random = new Random();

  def getRandomVocable() {
    def allVocables = allVocables()
    def vocableCount = allVocables.size()
    def randomVocableIndex = random.nextInt(vocableCount)
    allVocables[randomVocableIndex]
  }

  def allVocables() {
    Vocable.list()
  }
}
