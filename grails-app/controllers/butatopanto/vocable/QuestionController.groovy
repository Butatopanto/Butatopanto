package butatopanto.vocable

class QuestionController {

  def vocableService;

  def index = {
    redirect(action: "show", params: params)
  }

  def show = {
    [vocable: vocableService.getRandomVocable()]
  }

  def showFromList = {
    def studylist = Studylist.findByName(params.id)
    def model = [vocable: vocableService.getRandomVocable(studylist)]
    render(view: "show", model: model)
  }
}