package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())
  // functions without parameters could be called like this too:
  println(aParameterlessFunction)

  // When you need loops use Recursive functions
  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString  + aRepeatedFunction(aString, n-1)
  }

  println(aRepeatedFunction("hello", 3))

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  //code blocks with aux functions inside
  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b
    aSmallerFunction(n, n-1)
  }
  //Exercise
  // 1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old"

  def greeting(name: String, age: Int): Unit = {
    println(s"Hi, my name is $name and I am $age years old")
  }
  greeting("Jesus", 30)
  // 2. Factorial function 1 * 2 * 3  * .... * n
  def factorial(n: Int): Int = {
    if (n == 1) n else factorial(n-1)*n
  }
  println(factorial(4))
  // 3. A Fibonacci function: f(1) = 1, f(2) = 1, f(n) = f(n-1) + f(n-2)

  def fibonacci(n: Int): Int = {
    if (n > 0 & n < 3) 1 else fibonacci(n-1) + fibonacci(n-2)
  }
  println(fibonacci(14))
  //4. Test if a number is prime

  def prime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)
    isPrimeUntil(n/2)
  }
  println(prime(37))

}
