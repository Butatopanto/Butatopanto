class BootStrap {

  def init = { servletContext ->
    new butatopanto.kanji.bootstrapping.HeisigContent().initializeDatabase()
  }

  def destroy = {
  }
} 