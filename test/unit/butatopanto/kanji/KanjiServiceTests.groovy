package butatopanto.kanji

import grails.test.*
import static org.mockito.Mockito.*

class KanjiServiceTests extends GrailsUnitTestCase {

  def service

  protected void setUp() {
    super.setUp()
    service = new KanjiService()
  }

  void testReturnsNullIfNoKanjiExist() {
    mockDomain Kanji
    assertNull service.getRandomKanji()
  }

  void testReturnsSingleExtantKanji(){
    def kanji = new Kanji(meaning: 'Schatz')
    mockDomain Kanji, [kanji]
    assertEquals kanji, service.getRandomKanji()
  }

  void testReturnsKanjiAsDictatedByRandom(){
    def kanji1 = new Kanji(meaning: 'Schatz')
    def kanji2 = new Kanji(meaning: 'Nichts')
    mockDomain Kanji, [kanji1, kanji2]
    service.random =  mock(Random)
    when(service.random.nextInt(2)).thenReturn(1,0)
    assertEquals kanji2, service.getRandomKanji()
    assertEquals kanji1, service.getRandomKanji()
  }
}
