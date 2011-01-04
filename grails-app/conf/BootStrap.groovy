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
    def userRole = loadOrCreateRole('ROLE_USER')
    def adminRole = loadOrCreateRole('ROLE_ADMIN')
    createProductionUsers(userRole, adminRole)
    if (isDevelopment()) {
      createDevelopmentUsers(userRole, adminRole)
    }
  }

  private def loadOrCreateRole(def authority) {
    def role = Role.findByAuthority(authority)
    if (!role) {
      role = new Role(authority: authority).save(flush: true)
    }
    return role
  }

  private def isDevelopment() {
    return Environment.getCurrentEnvironment() == Environment.DEVELOPMENT
  }

  private def createDevelopmentUsers(Role userRole, Role adminRole) {
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

  private def createProductionUsers(Role userRole, Role adminRole) {
    if (!User.findByUsername('Admin')) {
      createAdmin(userRole, adminRole)
    }
  }

  private def createAdmin(Role userRole, Role adminRole) {
    def password = '0fcd568a5cb9bdb4677b69354b11ee415af8f784519cff3da49a26f84eaee7f2'
    def admin = new User(username: 'Admin', enabled: true, password: password)
    admin.save(flush: true)
    UserRole.create admin, userRole, true
    UserRole.create admin, adminRole, true
  }

  def destroy = {
  }
} 