import butatopanto.kanji.Frame
import butatopanto.security.Role
import butatopanto.security.User
import butatopanto.security.UserRole
import grails.util.Environment

class BootStrap {

  def heisigContentService
  def leitnerMigrationService
  def springSecurityService
  def storySynchronizationService

  def init = { servletContext ->
    heisigContentService.initializeDatabase()
    createUsers()
    leitnerMigrationService.updateDueDates()
    storySynchronizationService.copyUnknownStories()
  }

  def createUsers() {
    def userRole = loadOrCreateRole('ROLE_USER')
    def adminRole = loadOrCreateRole('ROLE_ADMIN')
    if (isDevelopment() || isTest() || isProduction()) {
      def allRoles = [userRole, adminRole]
      createTestUser('Urs', allRoles)
      createTestUser('Sandra', allRoles)
      createTestUser('Watashitachi', allRoles)
      createTestUser('Gast', [userRole])
      createAdmin(allRoles)
    }
  }

  private void createAdmin(def roles) {
    def password = '0fcd568a5cb9bdb4677b69354b11ee415af8f784519cff3da49a26f84eaee7f2'
    def admin = createUser('Admin', password, roles)
  }

  private void createTestUser(def name, def roles) {
    def password = springSecurityService.encodePassword('password')
    def user = createUser(name, password, roles)
  }

  private def createUser(name, password, roles) {
    if (!User.findByUsername(name)) {
      def user = createUser(name, password)
      grantPermissionsToUser user, roles
    }
  }

  private def createUser(name, password) {
    def user = new User(username: name, enabled: true, password: password)
    user.save(flush: true)
    return user
  }

  private void grantPermissionsToUser(def user, def roles) {
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