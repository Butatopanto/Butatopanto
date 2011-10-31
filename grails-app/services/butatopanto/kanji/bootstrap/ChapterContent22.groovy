package butatopanto.kanji.bootstrap

class ChapterContent22 extends ChapterContent {

  private static final int StartOfChapter = 578;
  private int nextNumber = StartOfChapter;

  ChapterContent22() {
    super(22)
  }

  @Override
  void addFramesToDatabase() {
    addKanji("曰", "zu sagen pflegen")
    addKanji("困", "in Verlegenheit")
    addKanji("固", "hart")
    addKanji("国", "Land")
    addKanji("団", "Gruppe")
    addKanji("因", "Ursache")
    addKanji("姻", "Ehe")
    addKanji("園", "Park")
    addKanji("回", "x-mal")
    addKanji("壇", "Podium")
    addKanji("店", "Laden")
    addKanji("庫", "Lagerhaus")
    addKanji("庭", "Garten")
    addKanji("庁", "Ministerium")
    addKanji("床", "Bett")
    addKanji("麻", "Hanf")
    addKanji("磨", "polieren")
    addKanji("心", "Herz")
    addKanji("忘", "vergessen")
    addKanji("忍", "erdulden")
    addKanji("認", "anerkennen")
    addKanji("忌", "Trauer")
    addKanji("志", "Absicht")
    addKanji("誌", "Zeitschrift")
    addKanji("忠", "Treue")
    addKanji("串", "Fleischspieß")
    addKanji("患", "befallen")
    addKanji("思", "denken")
    addKanji("恩", "Gunst")
    addKanji("応", "erwidern")
    addKanji("意", "Wille")
    addKanji("想", "Idee")
    addKanji("息", "Atem")
    addKanji("憩", "Rast")
    addKanji("恵", "Gnade")
    addKanji("恐", "Furcht")
    addKanji("惑", "unsicher")
    addKanji("感", "Gefühl")
    addKanji("憂", "Trübsinn")
    addKanji("寡", "verwitwet")
    addKanji("忙", "beschäftigt")
    addKanji("悦", "Entzücken")
    addKanji("恒", "beständig")
    addKanji("悼", "beklagen")
    addKanji("悟", "Erleuchtung")
    addKanji("怖", "schrecklich")
    addKanji("慌", "überstürzt")
    addKanji("悔", "bereuen")
    addKanji("憎", "hassen")
    addKanji("慣", "Gewohnheit")
    addKanji("愉", "Vergmügen")
    addKanji("惰", "faul")
    addKanji("慎", "Mäßigung")
    addKanji("憾", "Bedauern")
    addKanji("憶", "Gedächtnis")
    addKanji("慕", "Sehnsucht")
    addKanji("添", "beifügen")
    addKanji("必", "zwangsläufig")
    addKanji("泌", "absondern")
  }

  private addKanji(kanji, keyword){
    insertFrame(nextNumber, kanji, keyword)
    nextNumber++;
  }
}