package butatopanto.security

import butatopanto.kanji.HeisigUser

class UserService {

  def springSecurityService

  def findOrCreateHeisigUser() {
    if (!currentHeisigUser) {
      new HeisigUser(userName: currentUserName).save()
    }
    currentHeisigUser
  }

  def getCurrentUser() {
    def authentication = springSecurityService.authentication
    User.findByUsername(authentication.name)
  }

  HeisigUser getCurrentHeisigUser() {
    return HeisigUser.findByUserName(currentUserName)
  }

  private String getCurrentUserName() {
    currentUser.username
  }
}