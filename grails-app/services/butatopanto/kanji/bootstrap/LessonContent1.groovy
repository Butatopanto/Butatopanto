package butatopanto.kanji.bootstrap

import butatopanto.kanji.UserData
import butatopanto.kanji.Frame
import butatopanto.kanji.Story

class LessonContent1 extends LessonContent {

  LessonContent1() {
    super(1)
  }

  public void insertFrames() {
    insertFrame(1, "一", "eins")
    insertFrame(2, "二", "zwei")
    insertFrame(3, "三", "drei")
    insertFrame(4, "四", "vier")
    insertFrame(5, "五", "fünf")
    insertFrame(6, "六", "sechs")
    insertFrame(7, "七", "sieben")
    insertFrame(8, "八", "acht")
    insertFrame(9, "九", "neun")
    insertFrame(10, "十", "zehn")
    insertFrame(11, "口", "Mund")
    insertFrame(12, "日", "Tag")
    insertFrame(13, "月", "Monat")
    insertFrame(14, "田", "Reisfeld")
    insertFrame(15, "目", "Auge")
    insertSomeStories()
  }

  void insertSomeStories() {
    UserData userData = new UserData(userName: "Sandra").save()
    Frame.list().each {
      Story story = new Story(frame: it, text: "Eine tolle Geschichte für den Rahmen " + it.number)
      userData.addToStoryList(story)
    }
  }
}
