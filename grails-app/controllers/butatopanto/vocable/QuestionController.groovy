package butatopanto.vocable

class QuestionController {

  def vocableService;

  def index = {
    redirect(action: "show", params: params)
  }

  def show = {
    [vocable: vocableService.getRandomVocable()]
  }
}