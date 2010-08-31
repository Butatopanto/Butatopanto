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
      def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
      String password = springSecurityService.encodePassword('password')
      def urs = new User(username: 'Urs', enabled: true, password: password)
      urs.save(flush: true)
      def sandra = new User(username: 'Sandra', enabled: true, password: password)
      sandra.save(flush: true)
      UserRole.create sandra, userRole, true
      UserRole.create urs, userRole, true
    }
  }

  def destroy = {
  }
} 