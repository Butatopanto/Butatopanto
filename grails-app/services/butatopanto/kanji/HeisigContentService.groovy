package butatopanto.kanji

class HeisigContentService {

  def initializeDatabase() {
    log.info "Starting initialization of Heisig content."
    insertFirstLesson()
    insertSecondLesson()
    insertThirdLesson()
    insertForthLesson()
    insertFifthLesson()
    insertSixthLesson()
    insertSeventhLesson()
    log.info "Finished initialization of Heisig content."
  }

  private Frame insertFirstLesson() {
    insertFrame(1, "一", "eins")
    insertFrame(2, "二", "zwei")
    insertFrame(3, "三", "drei")
    insertFrame(4, "四", "vier")
    insertFrame(5, "五", "fünf")
    insertFrame(6, "六", "sechs")
    insertFrame(7, "七", "sieben")
    insertFrame(8, "八", "acht")
    insertFrame(9, "九", "neun")
    insertFrame(10, "十", "zehn")
    insertFrame(11, "口", "Mund")
    insertFrame(12, "日", "Tag")
    insertFrame(13, "月", "Monat")
    insertFrame(14, "田", "Reisfeld")
    insertFrame(15, "目", "Auge")
  }

  private Frame insertSecondLesson() {
    insertFrame(16, "古", "alt")
    insertFrame(17, "吾", "ich")
    insertFrame(18, "冒", "riskieren")
    insertFrame(19, "朋", "Gefährte")
    insertFrame(20, "明", "hell")
    insertFrame(21, "唱", "Gesang")
    insertFrame(22, "晶", "Kristall")
    insertFrame(23, "品", "Waren")
    insertFrame(24, "呂", "Rückgrat")
    insertFrame(25, "昌", "gedeihen")
    insertFrame(26, "早", "früh")
    insertFrame(27, "旭", "aufgehende Sonne")
    insertFrame(28, "世", "Generation")
    insertFrame(29, "胃", "Magen")
    insertFrame(30, "旦", "Morgendämmerung")
    insertFrame(31, "胆", "Gallenblase")
    insertFrame(32, "亘", "Spanne")
    insertFrame(33, "凹", "konkav")
    insertFrame(34, "凸", "konvex")
  }

  private Frame insertThirdLesson() {
    insertFrame(35, "旧", "alte Zeiten")
    insertFrame(36, "自", "selbst")
    insertFrame(37, "白", "weiß")
    insertFrame(38, "百", "hundert")
    insertFrame(39, "中", "in")
    insertFrame(40, "千", "tausend")
    insertFrame(41, "舌", "Zunge")
    insertFrame(42, "升", "Messkästchen")
    insertFrame(43, "昇", "aufsteigen")
    insertFrame(44, "丸", "rund")
    insertFrame(45, "寸", "Längenmaß")
    insertFrame(46, "専", "Spezialgebiet")
    insertFrame(47, "博", "Dr.")
    insertFrame(48, "占", "wahrsagen")
    insertFrame(49, "上", "auf")
    insertFrame(50, "下", "unter")
    insertFrame(51, "卓", "hervorragend")
    insertFrame(52, "朝", "Morgen")
  }

  private Frame insertForthLesson() {
    insertFrame(53, "只", "nur")
    insertFrame(54, "貝", "Muschel")
    insertFrame(55, "貞", "keusch")
    insertFrame(56, "員", "Mitglied")
    insertFrame(57, "見", "sehen")
    insertFrame(58, "児", "Säugling")
    insertFrame(59, "元", "Anfang")
    insertFrame(60, "頁", "Buchseite")
    insertFrame(61, "頑", "hartnäckig")
    insertFrame(62, "凡", "mittelmäßig")
    insertFrame(63, "負", "unterliegen")
    insertFrame(64, "万", "zehntausend")
    insertFrame(65, "句", "Ausdruck")
    insertFrame(66, "肌", "Oberfläche")
    insertFrame(67, "旬", "Zeitraum von zehn Tagen")
    insertFrame(68, "勺", "Kelle")
    insertFrame(69, "的", "Zielscheibe")
    insertFrame(70, "首", "Hals")
  }

  private Frame insertFifthLesson() {
    insertFrame(71, "乙", "delikat")
    insertFrame(72, "乱", "Aufruhr")
    insertFrame(73, "直", "direkt")
    insertFrame(74, "具", "Werkzeug")
    insertFrame(75, "真", "wahr")
    insertFrame(76, "工", "Handwerk")
    insertFrame(77, "左", "links")
    insertFrame(78, "右", "rechts")
    insertFrame(79, "有", "besitzen")
    insertFrame(80, "賄", "Bestechung")
    insertFrame(81, "貢", "Abgabe")
    insertFrame(82, "項", "Abschnitt")
    insertFrame(83, "刀", "Schwert")
    insertFrame(84, "刃", "Klinge")
    insertFrame(85, "切", "schneiden")
    insertFrame(86, "召", "locken")
    insertFrame(87, "昭", "gleißend")
    insertFrame(88, "則", "Regel")
    insertFrame(89, "副", "Vize-")
    insertFrame(90, "別", "getrennt")
    insertFrame(91, "丁", "Häuserblock")
    insertFrame(92, "町", "Stadt")
    insertFrame(93, "可", "möglich")
    insertFrame(94, "頂", "auf den Kopf")
  }

  private Frame insertSixthLesson() {
    insertFrame(95, "子", "Kind")
    insertFrame(96, "孔", "Ritze")
    insertFrame(97, "了", "fertig")
    insertFrame(98, "女", "Frau")
    insertFrame(99, "好", "mögen")
    insertFrame(100, "如", "als ob")
    insertFrame(101, "母", "Mama")
    insertFrame(102, "貫", "durchbohren")
    insertFrame(103, "兄", "älterer Bruder")
    insertFrame(104, "克", "überwinden")
  }

  private Frame insertSeventhLesson() {
    insertFrame(105, "小", "klein")
    insertFrame(106, "少", "wenig")
    insertFrame(107, "大", "groß")
    insertFrame(108, "多", "viele")
    insertFrame(109, "夕", "Abend")
    insertFrame(110, "汐", "abendliche Gezeiten")
    insertFrame(111, "外", "draußen")
    insertFrame(112, "名", "Name")
    insertFrame(113, "石", "Stein")
    insertFrame(114, "肖", "gleichen")
    insertFrame(115, "硝", "Salpeter")
    insertFrame(116, "砕", "zerschmettern")
    insertFrame(117, "砂", "Sand")
    insertFrame(118, "削", "hobeln")
    insertFrame(119, "光", "Licht")
    insertFrame(120, "太", "dick")
    insertFrame(121, "器", "Geschirr")
    insertFrame(122, "臭", "übel riechend")
    insertFrame(123, "妙", "bezaubernd")
    insertFrame(124, "省", "sich besinnen auf")
    insertFrame(125, "厚", "dicht")
    insertFrame(126, "奇", "seltsam")
  }

  def insertFrame(number, character, meaning) {
    Frame frame = Frame.findByNumber(number)
    if (frame != null) {
      return;
    }
    new Frame(number: number, character: character, meaning: meaning).save();
  }
}
