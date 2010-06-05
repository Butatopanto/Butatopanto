includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsWar")
includeTargets << grailsScript("_GrailsBootstrap")

target(main: "Builds a war file and publishes it") {
  war()
  uploadToGoogleCode()
}

target(uploadToGoogleCode: "Takes the war file and publishes it on the Google page") {
  def console = System.console();
  def username = console.readLine("Username? ")
  def password = console.readLine("Password? ")
  def summary = console.readLine("Content Summary? ")
  def filename = createFilename()
  def sourcefile = "target\\" + filename
  def targetfile = filename
  taskdef(name: "upload", classname: "net.bluecow.googlecode.ant.GoogleCodeUploadTask", classpath: "lib/ant-googlecode-0.0.2.jar")
  upload(username: username, password: password, projectname: "butatopanto", summary: summary, filename: sourcefile, targetfilename: targetfile, labels: "OpSys-All")
}

private def createFilename() {
  loadApp()
  def version = grailsApp.metadata.'app.version'
  "butatopanto-" + version + ".war"
}

setDefaultTarget(main)
