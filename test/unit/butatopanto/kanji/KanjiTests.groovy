package butatopanto.kanji

import grails.test.*

class KanjiTests extends GrailsUnitTestCase {

  protected void setUp() {
    super.setUp()
    def existingKanji = new Kanji(character: 'X', meaning: 'Schatz')
    mockForConstraintsTests(Kanji, [existingKanji])
  }

  void testEmptyKanjiIsInvalid() {
    def kanji = new Kanji()
    assertFalse kanji.validate()
  }

  void testKanjiWithMeaningAndCharacterIsValid() {
    assertTrue new Kanji(character: '?', meaning: 'Nichts').validate()
  }

  void testKanjiWithBlankMeaningIsInvalid() {
    assertFalse new Kanji(character: '?', meaning: '').validate()
  }

  void testKanjiWithoutCharacterIsInvalid() {
    assertFalse new Kanji(meaning: 'Nichts').validate()
  }

  void testKanjiWithEmptyCharacterIsInvalid() {
    assertFalse new Kanji(character: '', meaning: 'Nichts').validate()
  }

  void testKanjisCharacterIsUnique() {
    def kanji = new Kanji(character: 'X', meaning: 'Nichts')
    kanji.validate()
    assertEquals "unique", kanji.errors["character"]
  }

  void testKanjisMeaningIsUnique() {
    def kanji = new Kanji(character: 'Y', meaning: 'Schatz')
    kanji.validate()
    assertEquals "unique", kanji.errors["meaning"]
  }

  void testKanjisCharacterIsSingleCharacter() {
    def kanji = new Kanji(character: 'XY', meaning: 'Schatz')
    kanji.validate()
    assertEquals "maxSize", kanji.errors["character"]
  }
}
