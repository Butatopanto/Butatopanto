package butatopanto.kanji

import grails.test.GrailsUnitTestCase

class KanjiTests extends GrailsUnitTestCase {

  protected void setUp() {
    super.setUp()
    def existingKanji = new Kanji(character: 'X')
    mockForConstraintsTests(Kanji, [existingKanji])
  }

  void testEmptyKanjiIsInvalid() {
    def kanji = new Kanji()
    assertFalse kanji.validate()
  }

  void testKanjiWithMeaningAndCharacterIsValid() {
    assertTrue new Kanji(character: '?').validate()
  }

  void testKanjiWithEmptyCharacterIsInvalid() {
    assertFalse new Kanji(character: '').validate()
  }

  void testKanjisCharacterIsUnique() {
    def kanji = new Kanji(character: 'X')
    kanji.validate()
    assertEquals "unique", kanji.errors["character"]
  }

  void testKanjisCharacterIsSingleCharacter() {
    def kanji = new Kanji(character: 'XY')
    kanji.validate()
    assertEquals "maxSize", kanji.errors["character"]
  }
}
