import java.util.prefs.Preferences

includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsWar")
includeTargets << grailsScript("_GrailsBootstrap")

target(main: "Builds a war file and publishes it") {
  war()
  uploadToGoogleCode()
}

target(uploadToGoogleCode: "Takes the war file and publishes it on the Google page") {
  def console = System.console()
  def username = readUsername()
  def password = readPassword()
  saveUserNameAndPassword(username, password)
  def summary = console.readLine("Content Summary? ")
  def filename = createFilename()
  def sourcefile = "target\\" + filename
  def targetfile = filename
  taskdef(name: "upload", classname: "net.bluecow.googlecode.ant.GoogleCodeUploadTask")
  upload(username: username,
          password: password,
          projectname: "butatopanto",
          summary: summary,
          filename: sourcefile,
          targetfilename: targetfile,
          labels: "OpSys-All")
}

private def readUsername() {
  def username = loadUsername()
  if (username) {
    return username
  }
  def console = System.console()
  console.readLine("Username? ")
}

private def readPassword() {
  def password = loadPassword()
  if (password) {
    return password
  }
  def console = System.console()
  console.readLine("Password? ")
}

private def createFilename() {
  loadApp()
  def version = grailsApp.metadata.'app.version'
  "butatopanto-" + version + ".war"
}

private void saveUserNameAndPassword(def username, def password) {
  if (userWantsToSave()) {
    saveUsername username
    savePassword password
  }
}

private def userWantsToSave() {
  def console = System.console()
  def store = console.readLine("Do you want to store username and password? [y|n] ")
  store == 'y'
}

private def loadUsername() {
  loadPreference('username')
}

private def loadPassword() {
  loadPreference('password')
}

private void saveUsername(def username) {
  savePreference('username', username)
}

private void savePassword(def password) {
  savePreference('password', password)
}

private def loadPreference(def key) {
  Preferences userPreferences = Preferences.userRoot();
  userPreferences.get "butatopanto.distribution.${key}", null
}

private void savePreference(def key, def value) {
  Preferences userPreferences = Preferences.userRoot();
  userPreferences.put "butatopanto.distribution.${key}", value
}

setDefaultTarget(main)