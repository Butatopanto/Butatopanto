package butatopanto.security

import butatopanto.kanji.HeisigUser

class UserService {
  def static NO_USER = null
  def springSecurityService

  def findOrCreateHeisigUser() {
    if (!currentHeisigUser) {
      new HeisigUser(userName: currentUserName).save()
    }
    currentHeisigUser
  }

  def getCurrentUser() {
    def authentication = springSecurityService.authentication
    if (!authentication) {
      return NO_USER;
    }
    User.findByUsername(authentication.name)
  }

  HeisigUser getCurrentHeisigUser() {
    return HeisigUser.findByUserName(currentUserName)
  }

  private String getCurrentUserName() {
    currentUser.username
  }
}