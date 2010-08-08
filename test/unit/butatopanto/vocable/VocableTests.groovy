package butatopanto.vocable

import grails.test.*

class VocableTests extends GrailsUnitTestCase {
  protected void setUp() {
    super.setUp()
    mockForConstraintsTests(Vocable, [new Vocable()])
  }

  void testNewVocableIsNotValid() {
    assertFalse new Vocable().validate();
  }

  void testVocableWithMeaningAndKanaIsValid() {
    assertTrue new Vocable(meaning: "sehen", kana: "??").validate();
  }

  void testVocableWithMeaningOnlyIsNotValid() {
    assertFalse new Vocable(meaning: "sehen").validate();
  }

  void testVocableWithKanaOnlyIsNotValid() {
    assertFalse new Vocable(kana: "??").validate();
  }
}
