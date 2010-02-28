package net.butatopanto.vocable

class VocableService {

  boolean transactional = false

  def getRandomVocable() {
    def vocableCount = Vocable.count();
    def randomVocableIndex = new Random().nextInt(vocableCount)
    Vocable.list().getAt(randomVocableIndex)
  }
}
