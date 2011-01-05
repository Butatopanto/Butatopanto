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
        def allRoles = [userRole, adminRole]
        def urs = createTestUser('Urs')
        def sandra = createTestUser('Sandra')
        def gast = createTestUser('Gast')
        createAdmin(allRoles)
        grantPermissionsToUser sandra, allRoles
        grantPermissionsToUser urs, allRoles
        grantPermissionsToUser gast, [userRole]
      }
    }
  }

  private def createAdmin(def roles) {
    if (!User.findByUsername('Admin')) {
      def password = '0fcd568a5cb9bdb4677b69354b11ee415af8f784519cff3da49a26f84eaee7f2'
      def admin = createUser('Admin', password)
      grantPermissionsToUser(admin, roles)
    }
  }

  private def createTestUser(def name) {
    def password = springSecurityService.encodePassword('password')
    return createUser(name, password)
  }

  private def createUser(name, password) {
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