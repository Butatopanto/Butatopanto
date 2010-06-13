package butatopanto.vocable

import static org.mockito.Mockito.*
import grails.test.*

class VocableServiceTests extends GrailsUnitTestCase {

  VocableService service

  protected void setUp() {
    service = new VocableService();
    super.setUp()
  }

  void testReturnsNullForRandomVocableWithoutGivenVocables() {
    mockDomain Vocable
    assertNull service.randomVocable
  }

  void testReturnsVocableByRandomIndex() {
    mockDomain Vocable, [new Vocable(meaning: "first"), new Vocable(meaning: "second")]
    service.random = mock(Random)
    when(service.random.nextInt(2)).thenReturn(1, 1, 0)
    assertEquals "second", service.randomVocable.meaning
    assertEquals "second", service.randomVocable.meaning
    assertEquals "first", service.randomVocable.meaning
  }

  void testReturnsVocableFromList() {
    def list = new Studylist(vocables: [new Vocable(meaning: "onList")])
    mockDomain Studylist, [list]
    service.random = mock(Random)
    when(service.random.nextInt(1)).thenReturn(0)
    assertEquals "onList", service.getRandomVocable(list).meaning
  }
}
