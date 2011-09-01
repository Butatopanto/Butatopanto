package butatopanto.kanji

class ColorController {

     static defaultAction = "cssColor"

    def cssColor = {
        def controllerName = params.controller;
        render (controllerName == 'assembleReview' ? 'blue' : controllerName == 'mastery' ? 'green' : controllerName == 'flashcard' ? 'red' : 'white')
    }
}
