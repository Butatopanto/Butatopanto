package butatopanto.kanji;

import butatopanto.sharedtest.*
import org.junit.*
import static butatopanto.sharedtest.TagLibUtilities.getContentAsXml

class HeisigTagLibTest extends TagLibJUnit4TestCase {

  HeisigTagLibTest() {
    super(HeisigTagLib)
  }
  //assertEquals "", tagLib.out.getBuffer().toString()

// <div id='card' style='width:270px; height:390px; position:relative; top:50px; background-color:white' align='center' onclick="remoteFunction(1)">
// <table height = '100%'>
// <tr height = '10%'> <td style = 'text-align: left; font-size:20px'>the keyword</td></tr>
// <tr><td class = 'frameCardKanji' >?</td></tr>
// <tr height = '10%' ><td style = 'text-align: right; font-size:12px'></td></tr></table>
// </div>

  @Test
  void showsQuestionMarkForHiddenFrameCard() {
    tagLib.frameCard(hidden: true, frame: new Frame(id: 4, keyword: 'the keyword'))
    assertEquals "?", getKanjiTableData().text()
  }

  @Test
  void rendersHiddenKanjiTableDataWithFrameCardKanjiCssClass() {
    tagLib.frameCard(hidden: true, frame: new Frame(id: 4, keyword: 'the keyword'))
    assertEquals "frameCardKanji", getKanjiTableData().@class.text()
  }

  private def getKanjiTableData() {
    getContentAsXml(tagLib).table[0].tr[1].td[0]
  }
}