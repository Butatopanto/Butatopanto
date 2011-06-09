package butatopanto.kanji

class MasteryTagLib {

    static namespace = "mastery"

    def nextChapters = { attributes ->
        def navigation = attributes.navigation
        int chapters = navigation.nextChapters;
        int numberOfChaptersToShow = getNumberOfChaptersToShow(chapters);
        if (chapters) {
            for (chapter in navigation.chapterNumber + 1..navigation.chapterNumber + numberOfChaptersToShow) {
                writeLinkToChapterWithBox(chapter, 'next')
            }
        }
    }

    def previousChapters = { attributes ->
        def navigation = attributes.navigation
        int chapters = navigation.previousChapters;
        int numberOfChaptersToShow = getNumberOfChaptersToShow(chapters)
        if (chapters) {
            for (chapter in navigation.chapterNumber - numberOfChaptersToShow..navigation.chapterNumber - 1) {
                writeLinkToChapterWithBox(chapter, 'previous')
            }
        }
    }

    def flipDown = { attributes ->
        def navigation = attributes.navigation
        if (navigation.isOverrun()) {
            writeLinkToListByChapterWithImage('flip-down', navigation.chapterNumber, navigation.startIndex + 10) {
                "<img src='../../images/skin/arrow-down.png'/>"
            }
        }
    }

    def flipUp = { attributes ->
        def navigation = attributes.navigation
        if (navigation.isUnderrun()) {
            writeLinkToListByChapterWithImage('flip-up', navigation.chapterNumber, navigation.startIndex - 10) {
                "<img src='../../images/skin/arrow-up.png'/>"
            }
        }
    }

    def linkForPreviousKanji = { attributes ->
        def navigation = attributes.navigation
        def newIndex = navigation.startIndex - 10
        def chapter = navigation.chapterNumber
        linkToKanji(chapter, newIndex)
    }

    def linkForNextKanji = { attributes ->
        def navigation = attributes.navigation
        def newIndex = navigation.startIndex + 10
        def chapter = navigation.chapterNumber
        linkToKanji(chapter, newIndex);
    }

    private def writeLinkToChapterWithBox(chapterNumber, cssClass) {
        writeLinkToListByChapter(chapterNumber, "selector boxselector inactiveChapter " + cssClass, cssClass, 0) {
            chapterNumber
        }
    }

    private def writeLinkToListByChapterWithImage(cssClass, chapter, startIndex, closure) {
        out << "<div class='${cssClass}'>"
        writeLinkToListByChapter(chapter, "", cssClass, startIndex, closure)
        out << "</div>"
    }

    private def writeLinkToListByChapter(chapter, cssClassForLink, id, startIndex, closure) {
        out << g.link(controller: 'mastery', action: 'listByChapter', id: chapter, "class": cssClassForLink, elementId: id, params: [startIndex: startIndex]) {
            closure.call()
        }
    }

    private def linkToKanji(chapter, newIndex) {
        out << createLink(controller: 'mastery', action: 'listByChapter', id: chapter, params: [startIndex: newIndex])
    }

    private int getNumberOfChaptersToShow(int chapters) {
        return Math.min(3, chapters)
    }
}