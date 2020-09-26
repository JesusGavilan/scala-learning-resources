package lectures.part3fp

object WhatsAFunction extends App {

  // GOAL: use functions as first class elements
  // PROBLEM: OOP
  //example 1: using OOP -> using the trait

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler)
  //example 2: function types --> Function[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }
  println(stringToIntConverter("3") + 4)

  val added: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  println(added(5,6))

  // Function types Function2[A, B, R] === (A,B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /* Exercise:
   1. a function which takes 2 strings and concatenate them
   2. transform the MyPredicate and MyTransformer into function types
   3. define a function which takes an argument int and returns another function which takes an int and return an int
      - what's the type of this function
      - how to do it
   */

   //1.
  val concatenate: ((String, String) => String) = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  println(concatenate("jesus ", "is learning"))

  //3. Function1[Int, Function1[Int, Int]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) //curried function

}

trait MyFunction[A, B] {
  def apply(element: A): B
}
