class BootStrap {

  def heisigContentService;

  def init = { servletContext ->
    heisigContentService.initializeDatabase()
  }

  def destroy = {
  }
} 