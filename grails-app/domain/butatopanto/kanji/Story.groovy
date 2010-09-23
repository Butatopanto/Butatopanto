package butatopanto.kanji

import butatopanto.security.User

class Story {

  String text
  String kanji
  User user

  static constraints = {
    text(nullable: true, blank: true)
    user(nullable: false)
    kanji(nullable: false, blank: false, maxSize: 1, validator: {kanji, story ->
      def existingStory = Story.findByUserAndKanji(story.user, kanji)
      return existingStory == null
    })
  }
}
