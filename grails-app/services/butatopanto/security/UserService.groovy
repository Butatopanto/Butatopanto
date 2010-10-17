package butatopanto.security

import butatopanto.kanji.UserData

class UserService {

  def springSecurityService

  def findOrCreateUserData() {
    if (!currentUserData) {
      new UserData(userName: currentUserName).save()
    }
    currentUserData
  }

  def getCurrentUser() {
    def authentication = springSecurityService.authentication
    User.findByUsername(authentication.name)
  }

  UserData getCurrentUserData() {
    return UserData.findByUserName(currentUserName)
  }

  private String getCurrentUserName() {
    currentUser.username
  }
}