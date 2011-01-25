package butatopanto.kanji.mastery

import butatopanto.kanji.MasteryOfFrame
import butatopanto.sharedtest.UserSensitiveFunctionalTestCase

class MasteryRangeActivationFunctionalTest extends UserSensitiveFunctionalTestCase {

  void setUp() {
    super.setUp()
    logInDefaultUser()
    goToListOfSecondChapterPage()
    form() {
      from = 71
      to = 76
      click "activate"
    }
  }

  void tearDown() {
    def masteries = MasteryOfFrame.list()
    masteries.each {it.delete(flush: true)}
  }

  void testShowsChapterOfFromKanji() {
    assertTitle 'Chapter 5'
  }

  void testHasMasteriesForActivatedFrames() {
    def masteries = MasteryOfFrame.list()
    assertEquals(6, masteries.size())
  }

  private void goToListOfSecondChapterPage() {
    goToListPageForChapter 2
  }

  private void goToListPageForChapter(int chapter) {
    get("/mastery/listByChapter/${chapter}")
  }
}
