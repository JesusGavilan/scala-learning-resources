package lectures.part4implicits

import scala.concurrent.duration.DurationInt

object PimpMyLibrary extends App {
  //2.isPrime  ---> implicit class

  implicit class RichInt(val value: Int) extends AnyVal {
    def isEven: Boolean = value % 2 == 0
    def sqrt: Double    = Math.sqrt(value)
    def times(function: () => Unit): Unit = {
      def timesAux(n: Int): Unit =
        if (n <= 0) ()
        else {
          function()
          timesAux(n - 1)
        }
      timesAux(value)
    }
    def *[T](list: List[T]): List[T] = {
      def concatenate(n: Int): List[T] =
        if (n <= 0) List()
        else concatenate(n - 1) ++ list
      concatenate(value)
    }
  }

  new RichInt(42).sqrt

  42.isEven //type enrichment = pimping  --< new RichInt(42).isEven

  1 to 10
  3.seconds

  // compile doesn't do multiple implicit searches
  /*
    Enrich the string class
    - asInt Method
    - encrypt "John" -> Lnjp

    Keep enriching the Int class
    - times (function as a parameter)
    3.times(() => ...)
    3 * List(1,2) =>List(1,2,1,2,1,2)
   */

  implicit class RichString(value: String) {
    def asInt: Int                           = Integer.valueOf(value) // java.lang.Integer -> Int
    def encrypt(cypherDistance: Int): String = value.map(c => (c + cypherDistance).asInstanceOf[Char])
  }
  println("1".asInt + 4)
  println("doming".encrypt(2))

  3.times(() => println("Scala rocks!"))
  println(4 * List(1, 2))

  // "3" / 4
  implicit def stringToInt(string: String): Int = Integer.valueOf(string)

  println("6" / 2) // stringToInt("6") / 2
  //equivalent: implicit class RichAltInt(value: Int)
  class RichAltInt(value: Int)
  implicit def enrich(value: Int): RichAltInt = new RichAltInt(value)

  // danger zone
  implicit def intToBoolean(i: Int): Boolean = i == 1

  /*
  if (n)  do something
  else do something else
   */
  val aConditionedValue = if (3) "OK" else "Something wrong"
  println(aConditionedValue)

  // BEST PRACTICES:
  /*
 * Keep type enrichment to implicit classes and type classes
 * Avoid implicit defs as much as possible
 * package implicit clearly, bring into scope only what you need
 * IF you need conversions, make them specific, never with general types
 */

}
