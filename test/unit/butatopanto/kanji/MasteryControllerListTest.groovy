package butatopanto.kanji;


import butatopanto.request.BeanRequester
import butatopanto.sharedtest.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class MasteryControllerListTest extends GrailsJUnit4ControllerTestCase {
  int masteryCount = 0
  List masteryList = []
  int masteryListMax
  int masteryListOffset
  String masteryListSortAttribute
  String masteryListOrder

  MasteryControllerListTest() {
    super(MasteryController)
  }

  @Before
  void configureHeisigUserDataService() {
    controller.masteryService = [
      listMastery: {String sortAttribute, String order, int offset, int max ->
        masteryListMax = max
        masteryListOffset = offset
        masteryListSortAttribute = sortAttribute
        masteryListOrder = order
        masteryList
      },
      getMasteryCount: { masteryCount }
    ]
  }

  @Test
  void redirectsIndexToListFirstChapter() {
    controller.index()
    assertEquals([action: "listByChapter", id: 1], controller.redirectArgs)
  }

  @Test
  void shows20ActiveReviewsByDefault() {
    controller.list()
    assertEquals 20, masteryListMax
  }

  @Test
  void showsNumberOfActiveReviewsConfiguredInParameterMax() {
    controller.params.max = 3
    controller.list()
    assertEquals 3, masteryListMax
  }

  @Test
  void showsOnly100ActiveReviewsEvenIfMoreAreRequested() {
    controller.params.max = 101
    controller.list()
    assertEquals 100, masteryListMax
  }

  @Test
  void showsActiveReviewsStartingWithFirstByDefault() {
    controller.list()
    assertEquals 0, masteryListOffset
  }

  @Test
  void showsActiveReviewsStartingWithOffsetFromParameters() {
    controller.params.offset = 4
    controller.list()
    assertEquals 4, masteryListOffset
  }

  @Test
  void ordersActiveReviewsAccordingToFrameNumberByDefault() {
    controller.list()
    assertEquals "frame.number", masteryListSortAttribute
  }

  @Test
  void findsDefaultSortAttributeViaReflectionInFrameReview() {
    def review = new MasteryOfFrame(frame: new Frame(number: 4))
    def frameNumber = new BeanRequester(review).findProperty("frame.number")
    assertEquals 4, frameNumber
  }

  @Test
  void ordersActiveReviewsAccordingToPropertyFromSortParameter() {
    controller.params.sort = "my.test.property"
    controller.list()
    assertEquals "my.test.property", masteryListSortAttribute
  }

  @Test
  void ordersAscendingByDefault() {
    controller.list()
    assertEquals "Ascending", masteryListOrder
  }

  @Test
  void ordersDescendingWhenSpecifiedInOrderParameter() {
    controller.params.order = 'desc'
    controller.list()
    assertEquals "Descending", masteryListOrder
  }

  @Test
  void ordersAscendingWhenSpecifiedInOrderParameter() {
    controller.params.order = 'asc'
    controller.list()
    assertEquals "Ascending", masteryListOrder
  }

  @Test
  void returnsNumberOfActiveFrameReviewsFromHeisigUserDataService() {
    masteryCount = 7
    assertEquals 7, (controller.list().numberOfActiveFrameReviews)
  }

  @Test
  void returnsConfiguredActiveFrameListFromHeisigUserDataService() {
    masteryList = ["Der", "liebe", "Hasä"]
    assertEquals(["Der", "liebe", "Hasä"], controller.list().activeFrameReviewList)
  }
}