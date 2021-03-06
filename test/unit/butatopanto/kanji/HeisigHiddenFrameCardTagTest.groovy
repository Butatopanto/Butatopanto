package butatopanto.kanji;

import butatopanto.sharedtest.*
import org.junit.*
import static butatopanto.sharedtest.TagLibUtilities.getContentAsXml

class HeisigHiddenFrameCardTagTest extends TagLibJUnit4TestCase {
  private def contentXml

  HeisigHiddenFrameCardTagTest() {
    super(HeisigTagLib)
  }

  @Before
  void renderHiddenFrameCard() {
    tagLib.frameCard(hidden: true, frame: new Frame(id: 4, number: 5, kanji: 'k', keyword: 'the keyword'))
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
    assertTrue contentXml.@class.text().contains('frameCard')
  }

  @Test
  void hasOneThirdOfYuiSpace() {
    assertTrue contentXml.@class.text().contains('yui3-u-1-3')
  }

  @Test
  void hasRootWithOnClickRemoteFunction() {
    assertEquals 'remoteFunction(0)', contentXml.@onclick.text()
  }

  @Test
  void ajaxRevealsId4InContainerOnRemoteFunction() {
    assertEquals([action: 'ajaxReveal', update: 'container', id: 4L], remoteFunctions[0])
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
  void showsQuestionMarkForKanji() {
    assertEquals "?", getKanjiTableData().text()
  }

  @Test
  void hasFrameCardKanjiCssClassForKanjiTableData() {
    assertTrue getKanjiTableData().@class.text().contains("frameCardKanji")
  }

  @Test
  void hasKanjiCardIdForKanjiTableData() {
    assertEquals "kanji-card", getKanjiTableData().@id.text()
  }

  @Test
  void hasNumberTableRowWith10PercentHeight() {
    assertEquals "10%", getNumberTableRow().@height.text()
  }

  @Test
  void showsNoNumber() {
    assertEquals "", getNumberTableData().text()
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