object Demo extends App {

  var someOption = Some(3)
  var emptyOption = Option(None)

  var someList = List(1,2,3,4)
  var emptyList = List()

  // map(f)
  someOption.map(e => e).foreach(println) //

  //getOrElse(default)

  println(someOption.map(e => e)getOrElse("There is nothing"))//
  println(emptyOption.map(e => e).getOrElse("There is nothing"))//

  //fold(default)(f)
  println(someOption.fold(-1)(e => e))
//  println(emptyOption.fold(-1)(e => e))

  //isEmpty
  println(someList.isEmpty)

}
