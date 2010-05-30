package butatopanto.vocable

import grails.test.GrailsUnitTestCase

class VocableIntegrationTests extends GrailsUnitTestCase {

  void testIsValidWithMeaningAndKana() {
    def vocable = new Vocable(meaning: 'my meaning', kana: 'your kana')
    assertTrue "there should be no errors", vocable.validate()
  }

  void testMeaningMustNotBeBlank() {
    def vocable = new Vocable(meaning: "")
    vocable.validate()
    assertTrue vocable.errors.hasFieldErrors('meaning')
  }

  void testKanaMustNotBeBlank() {
    def vocable = new Vocable(kana: "")
    vocable.validate()
    assertTrue vocable.errors.hasFieldErrors('kana')
  }
}