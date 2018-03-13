
var someOption = Some(3)
var emptyOption = None
var list2Option = Some(5)
var listOption = Some(List(3,2,1))
var listSomeOption = List(Some(3), None, Some(5), None)

// map(f)
someOption.map(e => e) //
emptyOption.map(e => e) //

// flatMap(g(a))
someOption.flatMap(e => Option(e))
emptyOption.flatMap(e => Option(e))


//getOrElse(default)
someOption.map(e => e)getOrElse("There is nothing")//
emptyOption.map(e => e).getOrElse("There is nothing")//

//fold(default)(f)
someOption.fold(-1)(e => e)

//isEmpty
someOption.isEmpty
emptyOption.isEmpty

//isDefined
someOption.isDefined
emptyOption.isDefined

//nonEmpty (same as isDefined)
someOption.nonEmpty
emptyOption.nonEmpty

// orElse(Option(default))
someOption.orElse(Option("None"))
emptyOption.orElse(Option("None"))

// filter(p) or find(p)
someOption.filter(e => e == 3)
list2Option.filter(e => e == 3)

// filterNot(p)
someOption.filterNot(e => e == 1)
someOption.filterNot(e => e == 3)

// contains(b)
someOption contains 3
list2Option contains 3

//exists(p)
someOption.exists(_ == 3)
list2Option.exists(_ == 3)

// count(a)
someOption.count(_ == 3)
list2Option.count(_ == 3)

//Flatten
listSomeOption.flatten

// forall(p)
someOption.forall(e => e == 3)
someOption.forall(e => e == 2)
None.forall(e => e == 3)