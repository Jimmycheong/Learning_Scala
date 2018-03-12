object ImplicitsExample extends App{

  implicit val ec = "this is the execution context"

  def incrementor(i: Int)(implicit ec4: String): Int = {
    i + 1
  }

  var result = incrementor(2)

  println(result)

}
