package net.butatopanto.vocable

class QuestionController {

  def vocableService;

  def index = {
    redirect(action: "show", params: params)
  }

  def show = {
    def randomVocable = vocableService.getRandomVocable();
    [vocable: randomVocable]
  }
}