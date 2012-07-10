package butatopanto.kanji.bootstrap

class ChapterContent26 extends ChapterContent {

    private static final int StartOfChapter = 892;
    private int nextNumber = StartOfChapter;

    ChapterContent26() {
        super(26)
     }

    @Override
    void addFramesToDatabase() {
        addKanji("稿","Manuskript")
        addKanji("稼","Einkünfte")
        addKanji("程","Ausmaß")
        addKanji("税","Steuern")
        addKanji("稚","kindlich")
        addKanji("和","Harmonie")
        addKanji("移","verlagern")
        addKanji("秒","Sekunde")
        addKanji("秋","Herbst")
        addKanji("愁","Kummer")
        addKanji("私","privat")
        addKanji("秩","Ordnung")
        addKanji("秘","Geheimnis")
        addKanji("称","Bezeichnung")
        addKanji("利","Vorteil")
        addKanji("梨","Birnbaum")
        addKanji("穫","Ernte")
        addKanji("穂","Ähre")
        addKanji("稲","Reispflanze")
        addKanji("香","Weihrauch")
        addKanji("季","Jahreszeit")
        addKanji("委","Komittee")
        addKanji("秀","übertreffen")
        addKanji("透","durchsichtig")
        addKanji("誘","verleiten")
        addKanji("穀","Getreide")
        addKanji("菌","Bakterie")
        addKanji("米","Reis")
        addKanji("粉","mehl")
        addKanji("粘","klebrig")
        addKanji("粒","Korn")
        addKanji("粧","schminken")
        addKanji("迷","verirrt")
        addKanji("粋","schick")
        addKanji("糧","Proviant")
        addKanji("菊","Chrysantheme")
        addKanji("奥","Tiefe")
        addKanji("数","Zahl")
        addKanji("楼","Wachstum")
        addKanji("類","Sorte")
        addKanji("漆","Lack")
        addKanji("様","Gnädigste")
        addKanji("求","fordern")
        addKanji("球","Ball")
        addKanji("救","retten")
        addKanji("竹","Bambus")
        addKanji("笑","lachen")
        addKanji("笠","Bambushut")
        addKanji("笹","Bambusgras")
        addKanji("筋","Muskel")
        addKanji("箱","Schachtel")
        addKanji("筆","Schreibpinsel")
        addKanji("筒","Röhre")
        addKanji("等","usw.")
        addKanji("算","rechnen")
        addKanji("答","Antwort")
        addKanji("策","Strategie")
        addKanji("簿","Verzeichnis")
        addKanji("築","bauen")
    }

    private addKanji(kanji, keyword) {
        insertFrame(nextNumber, kanji, keyword)
        nextNumber++;
    }
}
