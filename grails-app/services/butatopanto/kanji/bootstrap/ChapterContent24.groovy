package butatopanto.kanji.bootstrap

class ChapterContent24 extends ChapterContent {

    private static final int StartOfChapter = 767;
    private int nextNumber = StartOfChapter;

    ChapterContent24() {
        super(24)
     }

    @Override
    void addFramesToDatabase() {
        addKanji("出","hinaus-")
        addKanji("山","Berg")
        addKanji("拙","ungeschickt")
        addKanji("岩","Felsen")
        addKanji("炭","Holzkohle")
        addKanji("岐","Abzweigung")
        addKanji("峠","Gebirgspass")
        addKanji("崩","zerfallen")
        addKanji("密","heimlich")
        addKanji("蜜","Honig")
        addKanji("嵐","Sturm")
        addKanji("崎","Vorgebirge")
        addKanji("入","hinein")
        addKanji("込","gedrängt")
        addKanji("分","Teil")
        addKanji("貧","arm")
        addKanji("頒","verteilen")
        addKanji("公","öffentlich")
        addKanji("松","Kiefer")
        addKanji("翁","Greis")
        addKanji("訟","verklagen")
        addKanji("谷","Tal")
        addKanji("浴","baden")
        addKanji("容","Inhalt")
        addKanji("溶","schmelzen")
        addKanji("欲","begehren")
        addKanji("裕","Überfluss")
        addKanji("鉛","Blei")
        addKanji("沿","entlang verlaufen")
    }

    private addKanji(kanji, keyword) {
        insertFrame(nextNumber, kanji, keyword)
        nextNumber++;
    }
}
