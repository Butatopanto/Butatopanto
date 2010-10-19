package butatopanto.security

import butatopanto.kanji.HeisigUser

class UserServiceObjectMother {

  static def defaultUserName = "The User"
  def service = [:]

  def setEnsuredCurrentUserDataExists() {
    setEnsuredCurrentUserDataExists(defaultUserName)
  }

  def setEnsuredCurrentUserDataExists(String userName) {
    def existingUserData = new HeisigUser(userName: userName).save()
    service["findOrCreateUserData"] = {
      existingUserData
    }
    service["currentUserData"] = existingUserData
  }

  void setEnsuredUserDataWillBeCreated() {
    service["findOrCreateUserData"] = {
      service["currentUserData"] = new HeisigUser(userName: defaultUserName).save()
    }
  }

  def setCurrentUserDataExists() {
    def userData = new HeisigUser(userName: "The User").save()
    service["currentUserData"] = userData
    userData
  }
}
