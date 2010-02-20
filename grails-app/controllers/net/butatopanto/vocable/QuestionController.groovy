package net.butatopanto.vocable

class QuestionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "show", params: params)
    }
  
    def show = {
      def vocableCount = Vocable.count();
      def randomVocableIndex = new Random().nextInt(vocableCount)
      def vocable = Vocable.list().getAt(randomVocableIndex)
      params.vocable = vocable;
      def question= new Question(vocable:vocable)
      [questionInstance: question]
    }

    def solve = {
      def vocableId = params.vocableId
      def vocable = Vocable.get(vocableId)
      def questionInstance = new Question(vocable: vocable)
      render(view: "solution", model: [questionInstance: questionInstance])
    }
}
