package butatopanto.kanji.mastery

import butatopanto.kanji.MasteryOfFrame
import butatopanto.sharedtest.UserSensitiveFunctionalTestCase

class MasteryRangeBadActivationFunctionalTest extends UserSensitiveFunctionalTestCase {

  void setUp() {
    super.setUp()
    logInDefaultUser()
    goToListOfSecondChapterPage()
  }


  void tearDown() {
    def masteries = MasteryOfFrame.list()
    masteries.each {it.delete(flush: true)}
  }

  void testIgnoresBadInputInToField() {
    form() {
      from = 71
      to = "abc"
      click "activate"
    }
    def masteries = MasteryOfFrame.findAll();
    assertEquals([], masteries)
  }

  void testShowsChapterOneAfterBadInputInToField() {
    form() {
      from = 71
      to = "abc"
      click "activate"
    }
    assertContentContains "Chapter 1"
  }

  void testNotifiesAboutBadInput() {
    form() {
      from = 71
      to = "abc"
      click "activate"
    }
    assertContentContains "Please enter the numbers of the first and last Kanji you want to add to your flashcards."
  }


  void testIgnoresBadInputInFromField() {
    form() {
      from = "abc"
      to = 76
      click "activate"
    }
    def masteries = MasteryOfFrame.findAll();
    assertEquals([], masteries)
  }

  void testShowsChapterOneAfterBadInputInFromField() {
    form() {
      from = "abc"
      to = 76
      click "activate"
    }
    assertContentContains "Chapter 1"
  }

  private void goToListOfSecondChapterPage() {
    goToListPageForChapter 2
  }

  private void goToListPageForChapter(int chapter) {
    get("/mastery/listByChapter/${chapter}")
  }
}
