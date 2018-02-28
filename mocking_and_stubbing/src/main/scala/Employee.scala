trait Person {
  val id : Int
  val firstName : String
  val lastName : String

  def getFullName: String

}

class Employee (val id: Int, val firstName: String, val lastName: String) extends Person {

  def getFullName: String = {
    firstName + " " + lastName
  }

}




