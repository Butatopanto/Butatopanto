package butatopanto.kanji

import butatopanto.sharedtest.GrailsJUnit4TestCase
import static butatopanto.sharedtest.ValidationUtilities.getValidationFieldError
import org.junit.Before
import org.junit.Test

class FrameTests extends GrailsJUnit4TestCase {

  def existingNumber = 12
  def existingMeaning = 'Schatz'
  def existingKanji = 'X'

  @Before
  public void mockFrameForConstraints() {
    def existingFrame = new Frame(number: 12, keyword: existingMeaning, kanji: existingKanji)
    mockForConstraintsTests(Frame, [existingFrame])
  }

  void isValidWithStringMeaning() {
    def frame = new Frame(keyword: 'other keyword')
    assertNull getValidationFieldError(frame, "keyword")
  }

  @Test
  void isInvalidWithBlankMeaning() {
    def frame = new Frame(keyword: '')
    assertNotNull getValidationFieldError(frame, "keyword")
  }

  @Test
  void isInvalidWithDuplicatedMeaning() {
    def frame = new Frame(keyword: existingMeaning)
    assertNotNull getValidationFieldError(frame, "keyword")
  }

  @Test
  void isValidWithNewHighNumber() {
    def frame = new Frame(number: existingNumber + 1)
    assertNull getValidationFieldError(frame, "number")
  }

  @Test
  void isInvalidWithNumberBelowOne() {
    def frame = new Frame(number: 0)
    assertNotNull getValidationFieldError(frame, "number")
  }

  @Test
  void isInvalidWithDuplicatedNumber() {
    def frame = new Frame(number: existingNumber)
    assertNotNull getValidationFieldError(frame, "number")
  }

  @Test
  void isValidWithUniqueSingleCharacterKanji() {
    def frame = new Frame(kanji: 'Y')
    assertNull getValidationFieldError(frame, "kanji")
  }

  @Test
  void isInvalidWithDuplicatedKanji() {
    def frame = new Frame(kanji: existingKanji)
    assertNotNull getValidationFieldError(frame, "kanji")
  }

  @Test
  void isInvalidWithLongKanjiString() {
    def frame = new Frame(kanji: 'XY')
    assertNotNull getValidationFieldError(frame, "kanji")
  }

  @Test
  void isInvalidWithEmptyKanji() {
    def frame = new Frame(kanji: '')
    assertNotNull getValidationFieldError(frame, "kanji")
  }

  @Test
  void isValidWithLessonOf1() {
    def frame = new Frame(chapter: 1)
    assertNull getValidationFieldError(frame, "chapter")
  }

  @Test
  void isInvalidWithLessonBelow1() {
    def frame = new Frame(chapter: 0)
    assertNotNull getValidationFieldError(frame, "chapter")
  }
}