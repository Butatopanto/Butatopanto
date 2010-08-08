package butatopanto.kanji

class HeisigTrainingController {

  def kanjiService

  def index = {
    redirect(action: "show", params: params)
  }

  def show = {
    [kanji: kanjiService.getRandomKanji()]
  }
}
