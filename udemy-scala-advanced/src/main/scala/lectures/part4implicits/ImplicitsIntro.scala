package lectures.part4implicits

object ImplicitsIntro extends App {
  val pair    = "Daniel" -> "555"
  val intPair = 1        -> 2

  case class Person(name: String) {
    def greet = s"Hi, my name is $name!"
  }

  implicit def fromStringToPerson(str: String): Person = Person(str)

  println("Peter".greet) // println(fromStringToPerson("Peter").greet)

  //implicit parameters
  def increment(x: Int)(implicit amount: Int) = x + amount
  implicit val defaultAmount                  = 10

  println(increment(2))

}
