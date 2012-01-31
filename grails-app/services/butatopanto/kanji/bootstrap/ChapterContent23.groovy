package butatopanto.kanji.bootstrap

import butatopanto.kanji.Frame

class ChapterContent23 extends ChapterContent {

    private static final int StartOfChapter = 637;
    private int nextNumber = StartOfChapter;

    ChapterContent23() {
        super(23)
        updateKeyword(665, "vorlegen")
        updateKeyword(628, "Vergnügen")
    }

    @Override
    void addFramesToDatabase() {
        addKanji("手", "Hand")
        addKanji("看", "beobachten")
        addKanji("摩", "reiben")
        addKanji("我", "Ego")
        addKanji("義", "Gerechtigkeit")
        addKanji("議", "Besprechung")
        addKanji("犠", "Opfer")
        addKanji("抹", "streichen")
        addKanji("抱", "hegen")
        addKanji("搭", "einsteigen")
        addKanji("抄", "Auszug")
        addKanji("抗", "sich widersetzen")
        addKanji("批", "Kritik")
        addKanji("招", "herbeiwinken")
        addKanji("拓", "urbar machen")
        addKanji("拍", "klatschen")
        addKanji("打", "schlagen")
        addKanji("拘", "verhaften")
        addKanji("捨", "wegwerfen")
        addKanji("拐", "entführen")
        addKanji("摘", "Prise")
        addKanji("挑", "herausfordern")
        addKanji("指", "Finger")
        addKanji("持", "halten")
        addKanji("括", "zusammenbinden")
        addKanji("揮", "schwingen")
        addKanji("推", "vermuten")
        addKanji("揚", "hissen")
        addKanji("提", "vorlegen")
        addKanji("損", "Schaden")
        addKanji("拾", "auflesen")
        addKanji("担", "schultern")
        addKanji("拠", "Basis")
        addKanji("描", "zeichnen")
        addKanji("操", "steuern")
        addKanji("接", "berühren")
        addKanji("掲", "Aushang")
        addKanji("掛", "hängen")
        addKanji("研", "schleifen")
        addKanji("戒", "Gebot")
        addKanji("械", "Apparat")
        addKanji("鼻", "Nase")
        addKanji("刑", "Strafe")
        addKanji("型", "Gussform")
        addKanji("才", "Genie")
        addKanji("財", "Vermögen")
        addKanji("材", "Baumaterial")
        addKanji("存", "existieren")
        addKanji("在", "sein")
        addKanji("乃", "des")
        addKanji("携", "tragbar")
        addKanji("及", "sich erstrecken")
        addKanji("吸", "saugen")
        addKanji("扱", "behandeln")
        addKanji("丈", "Längen")
        addKanji("史", "Historie")
        addKanji("吏", "Beamter")
        addKanji("更", "spät werden")
        addKanji("硬", "starr")
        addKanji("又", "einmal mehr")
        addKanji("双", "Paar")
        addKanji("桑", "Maulbeerbaum")
        addKanji("隻", "Seefahrzeug")
        addKanji("護", "schützen")
        addKanji("獲", "erbeuten")
        addKanji("奴", "Kerl")
        addKanji("怒", "wütend")
        addKanji("友", "Freund")
        addKanji("抜", "herausziehen")
        addKanji("投", "werfen")
        addKanji("没", "untergehen")
        addKanji("設", "einrichten")
        addKanji("撃", "Abschuss")
        addKanji("殻", "Hülse")
        addKanji("支", "Zweig")
        addKanji("技", "Technik")
        addKanji("枝", "Ast")
        addKanji("肢", "Gliedmaßen")
        addKanji("茎", "Stängel")
        addKanji("怪", "verdächtig")
        addKanji("軽", "leicht")
        addKanji("叔", "Onkel")
        addKanji("督", "Aufseher")
        addKanji("寂", "Einsamkeit")
        addKanji("淑", "anmutig")
        addKanji("反", "anti-")
        addKanji("坂", "slope")
        addKanji("板", "Brett")
        addKanji("返", "zurück")
        addKanji("販", "veräußern")
        addKanji("爪", "Kralle")
        addKanji("妥", "zufrieden")
        addKanji("乳", "Milch")
        addKanji("浮", "dahintreiben")
        addKanji("将", "Kommandeur")
        addKanji("奨", "fördern")
        addKanji("採", "pflücken")
        addKanji("菜", "Gemüse")
        addKanji("受", "annehmen")
        addKanji("授", "Verleihung")
        addKanji("愛", "Liebe")
        addKanji("払", "bezahlen")
        addKanji("広", "breit")
        addKanji("拡", "erweitern")
        addKanji("鉱", "Mineral")
        addKanji("弁", "Klappe")
        addKanji("雄", "männlich")
        addKanji("台", "Sockel")
        addKanji("怠", "vernachlässigen")
        addKanji("治", "regieren")
        addKanji("始", "beginnen")
        addKanji("胎", "Mutterleib")
        addKanji("窓", "Fenster")
        addKanji("去", "fort")
        addKanji("法", "Gesetz")
        addKanji("会", "Zusammenkunft")
        addKanji("至", "erreichen")
        addKanji("室", "Zimmer")
        addKanji("到", "ankommen")
        addKanji("致", "herbeiführen")
        addKanji("互", "gegenseitig")
        addKanji("棄", "verwerfen")
        addKanji("育", "aufziehen")
        addKanji("撤", "zurückziehen")
        addKanji("充", "füllen")
        addKanji("銃", "Gewehr")
        addKanji("硫", "Schwefel")
        addKanji("流", "Strömung")
        addKanji("允", "gestatten")
        addKanji("唆", "verführen")
    }

    private addKanji(kanji, keyword) {
        insertFrame(nextNumber, kanji, keyword)
        nextNumber++;
    }
}
