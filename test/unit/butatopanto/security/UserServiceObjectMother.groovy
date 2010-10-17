package butatopanto.security

import butatopanto.kanji.UserData

class UserServiceObjectMother {

  def defaultUserName = "The User"
  def service = [:]

  def setEnsuredCurrentUserDataExists() {
    setEnsuredCurrentUserDataExists(defaultUserName)
  }

  def setEnsuredCurrentUserDataExists(String userName) {
    def existingUserData = new UserData(userName: userName).save()
    service["findOrCreateUserData"] = {
      existingUserData
    }
    service["currentUserData"] = existingUserData
  }

  void setEnsuresUserDataWillBeCreated() {
    service["findOrCreateUserData"] = {
      service["currentUserData"] = new UserData(userName: defaultUserName).save()
    }
  }

  def setCurrentUserDataExists() {
    def userData = new UserData(userName: "The User").save()
    service["currentUserData"] = userData
    userData
  }
}
