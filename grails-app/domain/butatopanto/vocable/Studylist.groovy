package butatopanto.vocable

class Studylist {

  String name

  static constraints = {
    name(blank: false)
  }
  
  static hasMany = [vocables: Vocable]

  String toString(){
    name
  }
}
