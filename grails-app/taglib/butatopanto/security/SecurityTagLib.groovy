package butatopanto.security

class SecurityTagLib {

  static namespace = 'sec'

  def securityLink = { attributes ->
    sec.ifNotLoggedIn([]) {
      g.link(controller: "login", action: "auth") {'Login'}
    }
    sec.ifLoggedIn([]) {
      out << sec.username()
      out << ': '
      g.link(controller: "logout") {'Logout'}
    }
  }
}
