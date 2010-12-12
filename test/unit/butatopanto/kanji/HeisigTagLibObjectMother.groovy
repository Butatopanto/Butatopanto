package butatopanto.kanji


class HeisigTagLibObjectMother {
  def tagLib = [
          practiceTablet: {  attributes ->
            tagLib.frameCard(attributes) +
            tagLib.interaction(attributes)
          },
          frameCard: {attributes ->
            def hidden = attributes.hidden ? "" : "not "
            "Card for ${attributes.frame.id} is ${hidden}hidden."
          },
          interaction: {attributes ->
            def hidden = attributes.hidden ? "" : "not "
            "Interaction for ${attributes.frame.id} is ${hidden}hidden."
          }
  ]
}
