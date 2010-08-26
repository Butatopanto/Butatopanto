package butatopanto.kanji

import grails.test.*

class FrameTests extends GrailsUnitTestCase {
  def existingNumber = 12
  def existingMeaning = 'Schatz'
  def validNumber = existingNumber + 1
  def validMeaning = 'Indiana'
  def validKanji = new Kanji(character: 'Y')
  def existingKanji = new Kanji(character: 'X')

  protected void setUp() {
    super.setUp()
    def existingFrame = new Frame(number: 12, meaning: existingMeaning, kanji: existingKanji)
    mockForConstraintsTests(Frame, [existingFrame])
    mockForConstraintsTests(Kanji, [existingKanji])
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testIsInvalidIfEmpty() {
    def frame = new Frame()
    assertFalse frame.validate()
  }

  void testIsInvalidWithBlankMeaning() {
    def frame = new Frame(number: validNumber, meaning: '', kanji: validKanji)
    assertFalse frame.validate()
  }

  void testIsInvalidWithDuplicatedMeaning() {
    def frame = new Frame(number: validNumber, meaning: existingMeaning, kanji: validKanji)
    assertFalse frame.validate()
  }

  void testIsInvalidWithNumberBelowOne() {
    def frame = new Frame(number: 0, meaning: validMeaning, kanji: validKanji)
    assertFalse frame.validate()
  }

  void testIsInvalidWithDuplicatedNumber() {
    def frame = new Frame(number: existingNumber, meaning: validMeaning, kanji: validKanji)
    assertFalse frame.validate()
  }

  void testIsInvalidWithDuplicatedKanji() {
    def frame = new Frame(number: validNumber, meaning: validMeaning, kanji: existingKanji)
    assertFalse frame.validate()
  }
}