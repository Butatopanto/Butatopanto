package butatopanto.security

class UserService {

  def springSecurityService

  def getCurrentUser() {
    def authentication = springSecurityService.authentication
    User user = User.findByUsername(authentication.name)
    return user
  }
}