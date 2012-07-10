package butatopanto.kanji.bootstrap

class ChapterContent25 extends ChapterContent {

    private static final int StartOfChapter = 796;
    private int nextNumber = StartOfChapter;

    ChapterContent25() {
        super(25)
     }

    @Override
    void addFramesToDatabase() {
        addKanji("賞","Auszeichnung")
        addKanji("党","Partei")
        addKanji("堂","Halle")
        addKanji("常","üblich")
        addKanji("裳","Rock")
        addKanji("掌","Handfläche")
        addKanji("皮","Pelz")
        addKanji("波","Welle")
        addKanji("婆","Greisin")
        addKanji("披","eröffnen")
        addKanji("破","zerreißen")
        addKanji("被","sich etwas zuziehen")
        addKanji("残","Rest")
        addKanji("殉","Martyrium")
        addKanji("殊","insbesondere")
        addKanji("殖","vermehren")
        addKanji("列","Zug")
        addKanji("裂","spalten")
        addKanji("烈","entbrannt")
        addKanji("死","Tod")
        addKanji("葬","Beerdigung")
        addKanji("瞬","blinzeln")
        addKanji("耳","Ohr")
        addKanji("取","nehmen")
        addKanji("趣","Sinngehalt")
        addKanji("最","äußerst")
        addKanji("撮","knipsen")
        addKanji("恥","Scham")
        addKanji("職","Posten")
        addKanji("聖","heilig")
        addKanji("敢","kühn")
        addKanji("聴","horchen")
        addKanji("懐","Nostalgie")
        addKanji("慢","spöttisch")
        addKanji("漫","unbekümmert")
        addKanji("買","kaufen")
        addKanji("置","platzieren")
        addKanji("罰","ahnden")
        addKanji("寧","eher")
        addKanji("濁","trübe")
        addKanji("環","Ring")
        addKanji("還","zurückversetzen")
        addKanji("夫","Ehemann")
        addKanji("扶","Unterhalt")
        addKanji("渓","Gebirgsbach")
        addKanji("規","Norm")
        addKanji("替","umtauschen")
        addKanji("賛","preisen")
        addKanji("潜","untertauchen")
        addKanji("失","verlieren")
        addKanji("鉄","Eisen")
        addKanji("迭","abwechseln")
        addKanji("臣","Gefolgsmann")
        addKanji("姫","Prinzessin")
        addKanji("蔵","Depot")
        addKanji("臓","Eingeweihde")
        addKanji("賢","intelligent")
        addKanji("堅","solide")
        addKanji("臨","entgegensehen")
        addKanji("覧","besichtigen")
        addKanji("巨","riesig")
        addKanji("拒","abweisen")
        addKanji("力","Kraft")
        addKanji("男","Mann")
        addKanji("労","Mühe")
        addKanji("募","anwerben")
        addKanji("劣","minderwertig")
        addKanji("功","verdienstvoll")
        addKanji("勧","aufmuntern")
        addKanji("努","sich anstrengen")
        addKanji("励","anspornen")
        addKanji("加","hinzufügen")
        addKanji("賀","Gratulation")
        addKanji("架","installieren")
        addKanji("脇","Flanke")
        addKanji("脅","bedrohen")
        addKanji("協","ko-")
        addKanji("行","gehen")
        addKanji("律","Rhythmus")
        addKanji("復","wiederherstellen")
        addKanji("得","Nutzen")
        addKanji("従","folgen")
        addKanji("徒","vergeblich")
        addKanji("待","warten")
        addKanji("往","Fahrt")
        addKanji("征","unterwerfen")
        addKanji("径","Durchmesser")
        addKanji("彼","er")
        addKanji("役","Dienst")
        addKanji("徳","Tugend")
        addKanji("徹","durchdringen")
        addKanji("徴","Anzeichen")
        addKanji("懲","Sanktion")
        addKanji("微","winzig")
        addKanji("街","Boulevard")
        addKanji("衡","Gleichgewicht")
    }

    private addKanji(kanji, keyword) {
        insertFrame(nextNumber, kanji, keyword)
        nextNumber++;
    }
}
