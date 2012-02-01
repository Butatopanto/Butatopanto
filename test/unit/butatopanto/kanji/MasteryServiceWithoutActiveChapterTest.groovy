package butatopanto.kanji;


import butatopanto.security.UserServiceObjectMother
import butatopanto.sharedtest.GrailsJUnit4TestCase
import org.junit.Before
import org.junit.Test

class MasteryServiceWithoutActiveChapterTest extends GrailsJUnit4TestCase {

    private MasteryService service = new MasteryService()
    private UserServiceObjectMother userServiceObjectMother = new UserServiceObjectMother()
    private MasteryQueryServiceObjectMother masteryQueryServiceObjectMother = new MasteryQueryServiceObjectMother()

    @Before
    void activeChapterTwo() {
        mockUserService()
        mockMasteryQueryService()
        mockDomain MasteryOfFrame
        mockDomain Frame, [new Frame(id: 1, chapter: 1), new Frame(id: 2, chapter: 2), new Frame(id: 3, chapter: 2)]
    }

    private void mockUserService() {
        mockDomain HeisigUser
        service.userService = userServiceObjectMother.service
        userServiceObjectMother.setEnsuredCurrentHeisigUserExists()
    }

    private void mockMasteryQueryService() {
        masteryQueryServiceObjectMother.queryFromHeisigUserData UserServiceObjectMother.defaultUserName
        service.masteryQueryService = masteryQueryServiceObjectMother.service
    }

    @Test
    void firstChapterIsHindmost() {
        assertEquals 1, service.getHindmostMasteredChapter()
    }

    @Test
    void activatesOneMasteryOnActivationOfFirstChapter() {
        service.activateChapter(1)
        assertEquals 1, service.getMasteryCount()
    }
}
