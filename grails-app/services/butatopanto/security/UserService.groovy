package butatopanto.security

import butatopanto.kanji.UserData

class UserService {

  def springSecurityService

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