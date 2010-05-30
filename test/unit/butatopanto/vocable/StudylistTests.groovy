package butatopanto.vocable

import grails.test.*

class StudylistTests extends GrailsUnitTestCase {

  public void testNameMustNotBeBlank() {
    mockForConstraintsTests(Studylist)
    def noNameList = new Studylist()
    noNameList.validate()
    assertEquals "nullable", noNameList.errors['name']
  }

  public void testHasNoVocable() {
    assertNull new Studylist().vocables
  }
}
