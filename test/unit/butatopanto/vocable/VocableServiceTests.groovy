package butatopanto.vocable

import static org.mockito.Mockito.*
import grails.test.*

class VocableServiceTests extends GrailsUnitTestCase {

  TestVocableService service

  protected void setUp() {
    service = new TestVocableService();
    super.setUp()
  }

  void testReturnsNullForRandomVocableWithoutGivenVocables() {
    assertNull service.randomVocable
  }

  void testReturnsVocableByRandomIndex() {
    service.vocables = [new Vocable(meaning: "first"), new Vocable(meaning: "second")]
    service.random = mock(Random)
    when(service.random.nextInt(2)).thenReturn(1, 1, 0)
    assertEquals "second", service.randomVocable.meaning
    assertEquals "second", service.randomVocable.meaning
    assertEquals "first", service.randomVocable.meaning
  }

  protected void tearDown() {
    super.tearDown()
  }
}
