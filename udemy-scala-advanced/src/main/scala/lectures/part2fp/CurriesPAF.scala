package lectures.part2fp

object CurriesPAF extends App {
  // curried functions
  val superAdder: Int => Int => Int =
    x => y => x + y

  val add3 = superAdder(3) // Int => Int => y => 3 + y
  println(add3(5))
  println(superAdder(3)(5)) // curried functions ->  receives multiple parameter list

  // METHOD!
  def curriedAdder(x: Int)(y: Int): Int  = x + y // curried method
  // lifting = ETA-EXPANSION --> transforming a method into a function
  val add4: Int => Int = curriedAdder(4)

  // functions != methods (JVM limitation)
  def inc(x: Int) = x + 1
  List(1,2,3).map(inc) // ETA-expansion do it in the compiler as List(1,2,3).map(x => inc(x))

  // Partial function applications
  val add5 = curriedAdder(5) _ // convert this method into a function Int => Int

  // Exercise
  val simpleAddFunction = (x: Int, y: Int) => x + y
  def simpleAddMethod(x: Int, y: Int) = x + y
  def curriedAddMethod(x: Int)(y: Int) = x + y
  // define add7 function value -> add: Int => Int = y => 7 + y
  // as many different implementations of add7 using the above
  val add7 = (value: Int) => simpleAddFunction(value, 7)
  val add7_2 = simpleAddFunction.curried(7)
  def add7_3 = curriedAddMethod(7) _ // PAF: Partial applied function
  val add7_4 = curriedAddMethod(7) (_) // PAF =alternative syntax
  val add7_5 = simpleAddMethod(7, _: Int) //alternative syntax for turning methods into functions values -> y => sumplemethod(7, y
  val add7_6 = simpleAddFunction(7, _: Int)
  println(add7(3))
  println(add7_2(3))
  println(add7_3(3))
  println(add7_4(3))
  println(add7_5(3))
  println(add7_6(3))

  // undescores are powerful
  def concatenator(a: String, b: String, c: String) = a + b + c
  val insertName = concatenator("Hello, I'm ", _ : String, ", how, are you?") // x: String => concatenator("hello, x, "how are you?")
  println(insertName("Jesus"))

  val fillInTheBlanks = concatenator("Hello, ", _: String, _: String) // (x,y) => concatenator("Hello, ", x, y")
  println(fillInTheBlanks("Jesus"," Scala is cool"))

  // Exercises
  /*
    1. Process a list of numbers and return their string representation with different formats
      Use the %4.2f %8.6g and %14.12f with a curried formatter function*/
  def curriedFormatter(s: String)(number: Double): String = s.format(number)
  val numbers = List(Math.PI, Math.E, 1, 9.8, 1.3e-12)
  val simpleFormat = curriedFormatter("%4.2f") _ //lift
  val seriousFormat = curriedFormatter("%8.6f") _
  val preciseFormat = curriedFormatter("%14.12f") _
  println(numbers.map(simpleFormat)) // compiler does sweet eta-expansion for us
  println(numbers.map(preciseFormat))
  /*
    2. Difference between
    - function vs methods
    - parameters: by-name v 0-lambda
   */
  def byName(n: => Int) = n +1
  def byFunction(f: () => Int) = f() + 1
  def method: Int = 42
  def parenMethod(): Int = 42
  /*
   calling byName and byFunction
   - int
   - method
   - parenMethod
   - lambda
   - PAF
   Which compile, which not?
   */
  byName(23) //ok
  byName(method) // method will be evaluate
  byName(parenMethod())
  byName(parenMethod) // ok but beware ==> byName(parenMethod())
  //byName(() => 42) // not ok
  byName((() => 42)())// ok
  //byName(parenMethod _) // not ok
  //byFunction(45) // not ok
  //byFunction(method) // not Ok!!! does not do ETA-expansio!
  byFunction(parenMethod) // compiler does ETA-expansion
  byFunction(() => 46) //works
  byFunction(parenMethod _) //also works, but warning- unnecessary



}
