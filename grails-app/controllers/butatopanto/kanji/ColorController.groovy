package butatopanto.kanji

class ColorController {

     static defaultAction = "cssColor"

    def cssColor = {
        def controllerName = params.controller;
        render (controllerName == 'assembleReview' ? 'green' : controllerName == 'mastery' ? 'blue' : controllerName == 'flashcard' ? 'red' : 'white')
    }
}
