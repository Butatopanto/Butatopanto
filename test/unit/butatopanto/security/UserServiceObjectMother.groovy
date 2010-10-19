package butatopanto.security

import butatopanto.kanji.HeisigUser

class UserServiceObjectMother {

  static def defaultUserName = "The User"
  def service = [:]

  def setEnsuredCurrentHeisigUserExists() {
    setEnsuredCurrentHeisigUserExists(defaultUserName)
  }

  def setEnsuredCurrentHeisigUserExists(String userName) {
    def existingHeisigUser = new HeisigUser(userName: userName).save()
    service["findOrCreateHeisigUser"] = {
      existingHeisigUser
    }
    service["currentHeisigUser"] = existingHeisigUser
  }

  void setEnsuredHeisigUserWillBeCreated() {
    service["findOrCreateHeisigUser"] = {
      service["currentHeisigUser"] = new HeisigUser(userName: defaultUserName).save()
    }
  }

  def setCurrentHeisigUserExists() {
    def heisigUser = new HeisigUser(userName: defaultUserName).save()
    service["currentHeisigUser"] = heisigUser
    heisigUser
  }
}
