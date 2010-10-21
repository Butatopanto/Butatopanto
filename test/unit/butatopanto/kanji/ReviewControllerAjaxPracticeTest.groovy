package butatopanto.kanji;


import butatopanto.learning.Review
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class ReviewControllerAjaxPracticeTest extends GrailsJUnit4ControllerTestCase {

  ReviewControllerAjaxPracticeTest() {
    super(ReviewController)
  }

  @Before
  void mockDomainForFrameFive() {
    mockDomain Frame, [new Frame(id: 5)]
  }

  @Before
  void mockHeisigObjectMother() {
    controller.class.getMetaClass().heisig = new HeisigTagLibObjectMother().tagLib
  }

  @Test
  void turnsCardAndInteractionVisibleOnReveal() {
    controller.params.id = 5
    controller.ajaxReveal()
    assertEquals "Card for 5 is not hidden.Interaction for 5 is not hidden.", controller.response.contentAsString
  }
}