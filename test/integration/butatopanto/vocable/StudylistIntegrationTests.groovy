package butatopanto.vocable

import grails.test.GrailsUnitTestCase

class StudylistIntegrationTests extends GrailsUnitTestCase {

  def studylist = new Studylist(name: "Tiere")
  def vocable = new Vocable(meaning: "Bär", kana: "kuma")
  def doneInSetup = true

  protected void setUp() {
    super.setUp()
    vocable.addToLists(studylist)
  }

  public void testReceivesVocable() {
    assertEquals 1, studylist.vocables.size()
  }

  public void testSavesAndRemembersReceivedVocables() {
    studylist.save()
    def foundList = Studylist.findByName("Tiere");
    assertEquals "Bär", foundList.vocables.asList()[0].meaning
  }
}