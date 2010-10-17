package butatopanto

class StoryTagLib {

  static namespace = "heisig"

  def story = { attributes ->
    def text = attributes.text ?: g.message(code: 'review.story.unknown')
    out << "<p>${text}</p>"
  }
}
