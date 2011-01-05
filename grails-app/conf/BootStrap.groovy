import butatopanto.security.Role
import butatopanto.security.User
import butatopanto.security.UserRole
import grails.plugins.springsecurity.SpringSecurityService

class BootStrap {

  def heisigContentService
  def springSecurityService

  def init = { servletContext ->
    heisigContentService.initializeDatabase()
    createUsers()
  }

  def createUsers() {
    if (!Role.list()) {
      def userRole = loadOrCreateRole('ROLE_USER')
      def adminRole = loadOrCreateRole('ROLE_ADMIN')
      String password = springSecurityService.encodePassword('password')
      def urs = new User(username: 'Urs', enabled: true, password: password)
      urs.save(flush: true)
      def sandra = new User(username: 'Sandra', enabled: true, password: password)
      sandra.save(flush: true)
      def gast = new User(username: 'Gast', enabled: true, password: password)
      gast.save(flush: true)
      UserRole.create sandra, userRole, true
      UserRole.create sandra, adminRole, true
      UserRole.create urs, userRole, true
      UserRole.create urs, adminRole, true
      UserRole.create gast, userRole, true
    }
  }

  private def loadOrCreateRole(def authority) {
    def role = Role.findByAuthority(authority)
    if (!role) {
      role = new Role(authority: authority).save(flush: true)
    }
    return role
  }

  def destroy = {
  }
} 