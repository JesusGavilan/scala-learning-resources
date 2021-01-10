package lectures.part2fp

object LazyEvaluation extends App {
  // lazy DELAYS the evaluation of values
  lazy val x: Int = {
    println("hello")
    42
  }
  println(x)
  println(x)

  // examples of implications:
  // side effects
  def sideEffectCondition: Boolean = {
    println("Boo")
    true
  }
  def simpleCondition: Boolean = false

  lazy val lazyCondition = sideEffectCondition
  println(if (simpleCondition && lazyCondition) "yes" else "no")
  // simple condition is false so sideEffectCondition is never evaluated

  // in conjunction wtih call by name
  def byNameMethod(n: => Int): Int = {
    // CAL BY NEED
    lazy val t = n // only evaluated once
    t + t + t + 1
  }
  def retrieveMagicValue = {
    // side effect or a long computation
    println("waiting")
    Thread.sleep(1000)
    42
  }
  println(byNameMethod(retrieveMagicValue))

  // filtering with lazy vals
  def lessThan30(i: Int): Boolean = {
    println(s"$i is less than 30?")
    i < 30
  }

  def greaterThan20(i: Int): Boolean = {
    println(s"$i is bigger than 20?")
    i > 20
  }

  val numbers = List(1, 25, 40, 5, 23)
  val lt30 = numbers.filter(lessThan30) // List(1, 25, 5, 23)
  val gt20 = lt30.filter(greaterThan20)
  println(gt20)

  val lt30lazy = numbers.withFilter(lessThan30)
  val gt20lazy = lt30lazy.withFilter(greaterThan20)
  gt20lazy.foreach(println)

  // for-comprehensions use withFilter with guards
  for {
    a <- List(1,2,3) if a % 2 == 0 // use lazy vals!
  } yield a + 1
  List(1,2,3).withFilter(_ % 2 == 0).map(_ + 1)

  /*
    Exercise: Implement a lazily evaluated, singly linked STREAM of elements
    STREAM --> head always available, tail only available on demanda (lazy evaluated)

    naturals = MyStream.from(1)(x => x +1) = stream of natural numbers (potentially infinit!)
    naturals.take(100).foreach(println) // lazily evaluated stream of the first 100 naturals (finit stream)
    naturals.foreach(println) // wil crash - infinite
    naturals.map(_ * 2) // stream of all even numbers (potentially infinite)
    */


}
