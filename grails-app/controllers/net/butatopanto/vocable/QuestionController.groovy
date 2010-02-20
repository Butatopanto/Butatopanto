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
      def question= new Question(vocable:vocable)
      [questionInstance: question]
    }
}
