package butatopanto.kanji

class MasteredFrame {

  def frame
  def box
  boolean hasStory

  boolean isActive() {
    box != null && box > 0
  }

  def getCssClass() {
    def classes = ['kanjibox']
    classes.with {
      if (isActive()) {
        add("box${box}");
      }
      else {
        add('inactiveKanji')
      }
      if (hasStory) {
        add('withStory')
      }
      else if (isActive()) {
          add('withoutStory')
        }
      join(' ')
    }
  }
}
