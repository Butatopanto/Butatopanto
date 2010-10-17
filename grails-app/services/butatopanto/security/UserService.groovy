package butatopanto.security

import butatopanto.kanji.HeisigUser

class UserService {

  def springSecurityService

  def findOrCreateUserData() {
    if (!currentUserData) {
      new HeisigUser(userName: currentUserName).save()
    }
    currentUserData
  }

  def getCurrentUser() {
    def authentication = springSecurityService.authentication
    User.findByUsername(authentication.name)
  }

  HeisigUser getCurrentUserData() {
    return HeisigUser.findByUserName(currentUserName)
  }

  private String getCurrentUserName() {
    currentUser.username
  }
}