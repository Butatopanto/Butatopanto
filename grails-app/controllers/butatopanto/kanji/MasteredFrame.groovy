package butatopanto.kanji

class MasteredFrame {

  def frame
  def box
  boolean hasStory

  boolean isActive() {
    box != null && box > 0
  }

  def getCssClass() {
    if (isActive()) {
      def storyCssClass = hasStory ? 'withStory' : 'withoutStory'
      def boxCssClass = "box${box}"
      return "kanjibox $storyCssClass $boxCssClass"
    }
    return "kanjibox inactiveKanji"
  }
}
