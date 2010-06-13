package butatopanto.vocable

import grails.test.*

class StudylistTests extends GrailsUnitTestCase {

  void testNameMustNotBeBlank() {
    mockForConstraintsTests(Studylist)
    def noNameList = new Studylist()
    noNameList.validate()
    assertEquals "nullable", noNameList.errors['name']
  }

  void testHasNoVocable() {
    assertNull new Studylist().vocables
  }

  void testHasNameAsStringRepresentation() {
    assertEquals 'Liste', new Studylist(name: "Liste").toString()
  }
}
