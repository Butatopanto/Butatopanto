import grails.plugins.springsecurity.SpringSecurityService

import butatopanto.security.Role
import butatopanto.security.User
import butatopanto.security.UserRole
import grails.util.Environment

class BootStrap {

  def heisigContentService
  def springSecurityService

  def init = { servletContext ->
    heisigContentService.initializeDatabase()
    createUsers()
  }

  def createUsers() {
    if (isDevelopment() || isTest() || isProduction()){
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
        createAdminUser(sandra, userRole, adminRole)
        createAdminUser(urs, userRole, adminRole)
        UserRole.create gast, userRole, true
      }
    }
  }

  private def createAdminUser(User user, Role userRole, Role adminRole) {
    UserRole.create user, userRole, true
    UserRole.create user, adminRole, true
  }

  private boolean isProduction() {
    return Environment.getCurrentEnvironment() == Environment.PRODUCTION
  }

  private boolean isTest() {
    return Environment.getCurrentEnvironment() == Environment.TEST
  }

  private boolean isDevelopment() {
    return Environment.getCurrentEnvironment() == Environment.DEVELOPMENT
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