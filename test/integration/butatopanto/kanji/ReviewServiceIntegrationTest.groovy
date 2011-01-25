package butatopanto.kanji

import butatopanto.learning.Review
import butatopanto.security.User
import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Test

class ReviewServiceIntegrationTest extends GrailsJUnit4TestCase {

  ReviewService reviewService
  MasteryService masteryService
  def springSecurityService


  @Test
  void hasNoCurrentFrameForEmptyReview() {
    assertNull reviewService.getCurrentFrame(new Review())
  }

  @Test
  void startsReviewForGivenRange() {
    logInUser 'test'
    masteryService.activateRange 5, 7
    def review = reviewService.startRange(5, 7)
    assertTrue review.remainingIds.containsAll(5..7)
  }

  @Test
  void resolvesKanjiForRangeReview() {
    logInUser 'test'
    masteryService.activateRange 5, 6
    def review = reviewService.startRange(5, 6)
    review.currentReview = 5
    reviewService.resolveAndAdvance review, true
    assertTrue review.remainingIds.containsAll(6..6)
  }


  @Test
  void switchesToNextKanjiAfterResolve() {
    logInUser 'test'
    masteryService.activateRange 5, 6
    def review = reviewService.startRange(5, 6)
    review.currentReview = 5
    reviewService.resolveAndAdvance review, true
    assertEquals 6, review.currentReview
  }

  @Test
  void returnsFrameForCurrentReviewAfterResolve() {
    logInUser 'test'
    masteryService.activateRange 5, 6
    def review = reviewService.startRange(5, 6)
    review.currentReview = 5
    def frame = reviewService.resolveAndAdvance(review, true)
    assertEquals Frame.findById(6), frame
  }

  private void logInUser(def userName) {
    def password = "test"
    new User(username: userName, password: password).save(flush: true, failOnError: true)
    springSecurityService.reauthenticate(userName, password)
  }
}