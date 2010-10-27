package butatopanto.kanji;


import butatopanto.sharedtest.TagLibJUnit4TestCase
import org.junit.Before
import org.junit.Test
import static butatopanto.sharedtest.TagLibUtilities.getContentAsXml

class HeisigRevealedFrameCardTagTest extends TagLibJUnit4TestCase {
  private def contentXml

  HeisigRevealedFrameCardTagTest() {
    super(HeisigTagLib)
  }

  @Before
  void renderHiddenFrameCard() {
    tagLib.frameCard(hidden: false, frame: new Frame(id: 4, number: 5, kanji: 'k', keyword: 'the keyword'))
    contentXml = getContentAsXml(tagLib)
  }

  @Test
  void hasDivAsRoot() {
    assertEquals 'div', contentXml.name()
  }

  @Test
  void hasRootWithIdCard() {
    assertEquals 'card', contentXml.@id.text()
  }

  @Test
  void hasRootWithCssClassFrameCard() {
    assertEquals 'frameCard', contentXml.@class.text()
  }

  @Test
  void hasNoRemoteFunctionForClickOnRoot() {
    assertEquals '', contentXml.@onclick.text()
  }

  @Test
  void hasTableWith100PercentHeight() {
    assertEquals "100%", getFrameTable().@height.text()
  }

  @Test
  void hasKeywordTableRowWith10PercentHeight() {
    assertEquals "10%", getKeywordTableRow().@height.text()
  }

  @Test
  void showsKeyword() {
    assertEquals "the keyword", getKeywordTableData().text()
  }

  @Test
  void hasFrameCardKeywordCssClassForKeywordTableData() {
    assertEquals "frameCardKeyword", getKeywordTableData().@class.text()
  }

  @Test
  void showsKanjiCharacter() {
    assertEquals "k", getKanjiTableData().text()
  }

  @Test
  void hasFrameCardKanjiCssClassForKanjiTableData() {
    assertEquals "frameCardKanji", getKanjiTableData().@class.text()
  }

  @Test
  void hasNumberTableRowWith10PercentHeight() {
    assertEquals "10%", getNumberTableRow().@height.text()
  }

  @Test
  void showsNumber() {
    assertEquals "5", getNumberTableData().text()
  }

  @Test
  void hasFrameCardNumberCssClassForNumberTableData() {
    assertEquals "frameCardNumber", getNumberTableData().@class.text()
  }

  private def getKeywordTableData() {
    getKeywordTableRow().td[0]
  }

  private def getKeywordTableRow() {
    getFrameTable().tr[0]
  }

  private def getKanjiTableData() {
    getFrameTable().tr[1].td[0]
  }

  private def getNumberTableData() {
    getNumberTableRow().td[0]
  }

  private def getNumberTableRow() {
    getFrameTable().tr[2]
  }

  private def getFrameTable() {
    contentXml.table[0]
  }
}