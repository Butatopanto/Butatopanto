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
    if (isDevelopment() || isTest() || isProduction()) {
      if (!Role.list()) {
        def userRole = loadOrCreateRole('ROLE_USER')
        def adminRole = loadOrCreateRole('ROLE_ADMIN')
        def urs = createUser('Urs')
        def sandra = createUser('Sandra')
        def gast = createUser('Gast')
        grantPermissionsToUser sandra, [userRole, adminRole]
        grantPermissionsToUser urs, [userRole, adminRole]
        grantPermissionsToUser gast, [userRole]
      }
    }
  }

  private def createUser(def name) {
    def password = springSecurityService.encodePassword('password')
    def user = new User(username: name, enabled: true, password: password)
    user.save(flush: true)
    return user
  }

  private def grantPermissionsToUser(def user, def roles) {
    roles.each { def role ->
      UserRole.create user, role, true
    }
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