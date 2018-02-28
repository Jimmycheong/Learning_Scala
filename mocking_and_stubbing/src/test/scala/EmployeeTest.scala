import org.scalamock.scalatest.MockFactory
import org.scalatest.Matchers

class EmployeeTest extends Matchers with MockFactory {

  val fakeEmployee: Person = mock[Person]

  (fakeEmployee.getFullName _).expects().returns("John Smith")

  println("Testing stub object")

  fakeEmployee.getFullName shouldBe "John Smith"

}
