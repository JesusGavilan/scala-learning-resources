package lectures.part4implicits

object OrganizingImplicits extends App {
  implicit val reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  println(List(1, 4, 5, 3, 2).sorted)

  //scala.Predef
  /* Potential Implicits (used as implicit parameters) :
     - val/var
     - object
     - accessor methods = defs with no parentheses
   */

  // Exercise: alphanumeric ordering

  case class Person(name: String, age: Int)

  val persons = List(
    Person("Steve", 30),
    Person("Amy", 22),
    Person("John", 66)
  )
  //implicit val alphabeticOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)

  object AlphabeticNameOrdering {
    implicit val alphabeticOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)
  }

  object AgeOrdering {
    implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.age < b.age)
  }

  import AgeOrdering._

  println(persons.sorted)

  /*
      Implicit scope
      - normal scope = LOCAL SCOPE
      - imported scope =
      - companion of all types involved in the method signature
       - List
       - Ordering
       - all the types involved = A or any supertype
   */

  /*
   Exercise.
   - totalPrice = most used (50%)
   - by unit count = 25%
   - by unit price = 25%
   */
  case class Purchase(nUnits: Int, unitPrice: Double)

  object Purchase { // put this implict here in the companion because totalPrice ordering is the most common used.
    implicit val totalPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((a, b) => a.nUnits * a.unitPrice < b.nUnits * b.unitPrice)
  }

  object byUnitsOrdering {
    implicit val unitsOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.nUnits < _.nUnits)
  }
  object byUnitPriceOrdering {
    implicit val unitPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((_.unitPrice < _.unitPrice))
  }
  import byUnitsOrdering._

  val purchases = List(
    Purchase(2, 10.0),
    Purchase(4, 1.0),
    Purchase(12, 4.0)
  )
  println(purchases.sorted)

}
