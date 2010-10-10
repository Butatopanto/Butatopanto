package butatopanto.kanji;


import butatopanto.request.BeanRequester
import butatopanto.test.GrailsJUnit4ControllerTestCase
import org.junit.Before
import org.junit.Test

class ReviewControllerListTest extends GrailsJUnit4ControllerTestCase {
  int activeReviewCount = 0
  List activeReviewList = []
  int reviewListMax
  int reviewListOffset
  String reviewListSortAttribute
  String reviewListOrder

  ReviewControllerListTest() {
    super(ReviewController)
  }

  @Before
  void configureHeisigUserDataService() {
    controller.heisigUserDataService = [
      listActiveReviews: {String sortAttribute, String order, int offset, int max ->
        reviewListMax = max
        reviewListOffset = offset
        reviewListSortAttribute = sortAttribute
        reviewListOrder = order
        activeReviewList
      },
      getReviewCount: { activeReviewCount }
    ]
  }

  @Test
  void shows20ActiveReviewsByDefault() {
    controller.list()
    assertEquals 20, reviewListMax
  }

  @Test
  void showsNumberOfActiveReviewsConfiguredInParameterMax() {
    controller.params.max = 3
    controller.list()
    assertEquals 3, reviewListMax
  }

  @Test
  void showsOnly100ActiveReviewsEvenIfMoreAreRequested() {
    controller.params.max = 101
    controller.list()
    assertEquals 100, reviewListMax
  }

  @Test
  void showsActiveReviewsStartingWithFirstByDefault() {
    controller.list()
    assertEquals 0, reviewListOffset
  }

  @Test
  void showsActiveReviewsStartingWithOffsetFromParameters() {
    controller.params.offset = 4
    controller.list()
    assertEquals 4, reviewListOffset
  }

  @Test
  void ordersActiveReviewsAccordingToFrameNumberByDefault() {
    controller.list()
    assertEquals "frame.number", reviewListSortAttribute
  }

  @Test
  void findsDefaultSortAttributeViaReflectionInFrameReview() {
    def review = new FrameReview(frame: new Frame(number: 4))
    def frameNumber = new BeanRequester(review).findProperty("frame.number")
    assertEquals 4, frameNumber
  }

  @Test
  void ordersActiveReviewsAccordingToPropertyFromSortParameter() {
    controller.params.sort = "my.test.property"
    controller.list()
    assertEquals "my.test.property", reviewListSortAttribute
  }

  @Test
  void ordersAscendingByDefault() {
    controller.list()
    assertEquals "Ascending", reviewListOrder
  }

  @Test
  void ordersDescendingWhenSpecifiedInOrderParameter() {
    controller.params.order = 'desc'
    controller.list()
    assertEquals "Descending", reviewListOrder
  }

  @Test
  void ordersAscendingWhenSpecifiedInOrderParameter() {
    controller.params.order = 'asc'
    controller.list()
    assertEquals "Ascending", reviewListOrder
  }

  @Test
  void returnsNumberOfActiveFrameReviewsFromHeisigUserDataService() {
    activeReviewCount = 7
    assertEquals 7, (controller.list().numberOfActiveFrameReviews)
  }

  @Test
  void returnsConfiguredActiveFrameListFromHeisigUserDataService() {
    activeReviewList = ["Der", "liebe", "Hasä"]
    assertEquals(["Der", "liebe", "Hasä"], controller.list().activeFrameReviewList)
  }
}