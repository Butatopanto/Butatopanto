package butatopanto.vocable

class QuestionController {

  def vocableService;

  def index = {
    redirect(action: "show", params: params)
  }

  def show = {
    if (params.id) {
      def studylist = Studylist.findByName(params.id)
      [vocable: vocableService.getRandomVocable(studylist)]
    }
    else {
      [vocable: vocableService.getRandomVocable()]
    }
  }
}