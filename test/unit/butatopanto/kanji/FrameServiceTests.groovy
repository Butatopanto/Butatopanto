package butatopanto.kanji

import grails.test.*
import static org.mockito.Mockito.*

class FrameServiceTests extends GrailsUnitTestCase {

  def service

  protected void setUp() {
    super.setUp()
    service = new FrameService()
  }

  void testReturnsNullIfNoFrameExist() {
    mockDomain Frame
    assertNull service.getRandomFrame()
  }

  void testReturnsSingleExtantFrame(){
    def frame = new Frame(meaning: 'Schatz')
    mockDomain Frame, [frame]
    assertEquals frame, service.getRandomFrame()
  }

  void testReturnsKanjiAsDictatedByRandom(){
    def frame1 = new Frame(meaning: 'Schatz')
    def frame2 = new Frame(meaning: 'Nichts')
    mockDomain Frame, [frame1, frame2]
    service.random =  mock(Random)
    when(service.random.nextInt(2)).thenReturn(1,0)
    assertEquals frame2, service.getRandomFrame()
    assertEquals frame1, service.getRandomFrame()
  }
}
