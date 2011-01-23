package butatopanto.progress

class CardBoxIdentifier {

  private def romans = ['O', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII']

  def identify(arabic) {
    romans.get(arabic)
  }
}
