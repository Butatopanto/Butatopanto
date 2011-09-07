package butatopanto.kanji

import butatopanto.learning.Review
import grails.plugins.springsecurity.Secured

@Secured('ROLE_USER')
class ReviewController {

    static navigation = [group:'tabs', title: "review", order: 25, action: 'practice', isVisible: {
        session.review
    }]
    def reviewService

    def currentStory = {
        def frameId = session.review.currentReview
        redirect(controller: "story", action: "show", id: frameId, params: [uriToShowAfterSave:"/review/practice"])
    }

    def practice = {
        [frame: reviewService.getCurrentFrame(session.review)]
    }

    def ajaxReveal = {
        def frame = Frame.get(params.id)
        ajaxRenderFrame(frame, false)
    }

    def ajaxResolve = {
        boolean reviewCorrect = params.reviewCorrect == "true"
        Review review = session.review
        def frame = reviewService.resolveAndAdvance(review, reviewCorrect)
        if (frame) {
            ajaxRenderFrame(frame, true)
        }
        else {
            endReview()
        }
    }

    def repeat = {
        def kanji = params.kanji.minus('[').minus(']').split(',')
        flash.kanji = kanji.collect {it.toInteger()}
        redirect(controller: 'assembleReview', action: 'startList')
    }

    def startNew = {
        redirect(controller: 'assembleReview')
    }

    private def ajaxRenderFrame(frame, boolean hidden) {
        def practiceTablet = heisig.practiceTablet([frame: frame, hidden: hidden])
        render practiceTablet
    }

    private def endReview() {
        def finishedReview = session.review;
        session.review = null;
        render(template: 'endReview', model: [review: finishedReview])
    }
}