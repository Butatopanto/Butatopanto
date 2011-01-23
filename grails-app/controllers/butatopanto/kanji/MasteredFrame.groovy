package butatopanto.kanji

class MasteredFrame {

  def frame
  def box
  boolean hasStory

  boolean isActive() {
    box != null && box > 0
  }

  def getCssClass() {
    def classes = []
    classes.add('kanjibox')
    if (isActive()) {
      classes.add("box${box}");
      if (!hasStory) {
        classes.add('withoutStory')
      }
    }
    else {
      classes.add('inactiveKanji')
    }
    if (hasStory) {
      classes.add('withStory')
    }
    return classes.join(' ')
  }
}
