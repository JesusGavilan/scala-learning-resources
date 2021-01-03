package lectures.part1as

object Recap extends App {
  val aCondition: Boolean = false
  val aConditionVal = if (aCondition) 42 else 65
  // instructions vs expressions

  // compiler infers types for us
  val aCodeBlock = {
    if (aCondition) 54
    56
  }

  //Unit = void
  val theUnit = println("hello, Scala")

  // functions
  def aFunction(x: Int): Int = x + 1
  // recursion
  @tailrec
  def factorial(n: Int, accumulator: Int): Int =
    if (n <= 0) accumulator
    else factorial(n - 1, n * accumulator)

  // object-orientied programming
  class Animal
  class Dog extends Animal
  val aDog: Animal = new Dog //subtying polymorphism

  trait Carnivore {
    def eat(a: Animal): Unit
  }

  class Cocodrile extends Animal with Carnivore {
    override def eat(a: Animal): Unit = println("crunch!")
  }

  // method notations
  val aCroc = new Cocodrile
  aCroc.eat(aDog)
  aCroc eat aDog // natural language

  // anonymous classes
  val aCarnivore = new Carnivore {
    override def eat(a: Animal): Unit = println("roar!")
  }
  // generics
  abstract class MyList[+A] // variance and variance problems

  //singletons and companions
  object MyList

  // case classes
  case class Person(name: String, age: Int)

  //exceptions and try/catch finally
  val throwsExceptions = throw new RuntimeException // Nothing
  val aPotentialFailure = try {
    throw new RuntimeException
  } catch {
    case e: Exception => "I caught an exception"
  } finally {
    println("some logs")
  }

  // packaging and imports

  // functional programming
  val incrementer = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }

  incrementer(1)

  val anonymousIncrementer = (x: Int) => x + 1

  List(1,2,3).map(anonymousIncrementer) // HOF
  // map, flatMap, filter
  // for-comprehension
  val pairs = for {
    num <- List(1,2,3) // if condition
    char <- List('a', 'b', 'c')
  } yield num + "-" + char

  // Scala collecstions: Seq, Arrays, List, Vectors, Maps, Tuples
  val aMap = Map(
    "Jesus" -> 556,
    "Marta" -> 665,
  )

  // "collections": Options, Try
  val anOption = Some(2)

  // pattern matching
  val x = 2
  val order = x match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => x + "th"
  }

  val bob = Person("Bob", 22)
  val greeting = bob match {
    case Person(n, _) => s"Hi, my name is $n"
  }

  //all the patterns

}
