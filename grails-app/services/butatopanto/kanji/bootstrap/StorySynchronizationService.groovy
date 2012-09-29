package butatopanto.kanji.bootstrap

import butatopanto.kanji.HeisigUser
import butatopanto.kanji.Story

class StorySynchronizationService {

    def copyUnknownStories() {
        log.info "Started synchronization of stories."
        copyUnknownStories('Urs')
        copyUnknownStories('Watashitachi')
        log.info "Finished synchronization of stories."
    }

    private def copyUnknownStories(userName) {
        def sandraUser = getOrCreateHeisigUserFor('Sandra')
        def recipientUser = getOrCreateHeisigUserFor(userName)
        sandraUser.storyList.each {
            copyIfUnknown(it, recipientUser)
        }
    }

    private HeisigUser getOrCreateHeisigUserFor(userName) {
        HeisigUser user = HeisigUser.findByUserName(userName)
        if (user) {
            return user
        }
        HeisigUser newUser = new HeisigUser(userName: userName);
        newUser.save(failOnError: true);
        return newUser
    }

    private def copyIfUnknown(Story story, HeisigUser heisigUser) {
        def stories = Story.withCriteria {
            and {
                user {
                    eq('userName', heisigUser.userName)
                }
                frame {
                    eq('number', story.frame.number)
                }
            }
        }
        if (stories == null || stories.empty) {
            heisigUser.addToStoryList(new Story(frame: story.frame, text: story.text))
        }
    }
}