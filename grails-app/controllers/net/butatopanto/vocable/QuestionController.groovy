package net.butatopanto.vocable

class QuestionController {

  def vocableService;

  def index = {
    redirect(action: "show", params: params)
  }

  def show = {
    def vocable = vocableService.getRandomVocable();
    def question = new Question(vocable: vocable)
    [questionInstance: question]
  }
}