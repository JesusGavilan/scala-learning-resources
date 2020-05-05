package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n-1))
      val result = n * factorial(n-1)
      println("Computed factorial of " + n)
      result
    }
  //println(factorial(10)) //This will break with big numbers


  // The right approach to implement recursion: TAIL RECURSION
  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x -1, x * accumulator) // TAIL RECURSION = use recursive call as the LAST EXPRESSION

    factHelper(n, 1)
  }
  println(anotherFactorial(50))

  // When you need loops, use TAIL RECURSION

  //Execises:
  //1. Concatenate a string n times using tail recursion
  @tailrec
  def concatTailRec(x: Int,  aString: String, aStringAccumulator: String): String =
    if (x <= 0) aStringAccumulator
    else concatTailRec(x-1, aString, aString + aStringAccumulator)

  println(concatTailRec(5, "hola|", ""))

  //2. IsPrime function tail recursive
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailRec(t-1, n % t!=0 && isStillPrime)
    isPrimeTailRec(n/2, true)
  }
  println(isPrime(2003))
  println(isPrime(629))

  //3. Fibonacci function, tail recursive
  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciTailRec(x: Int, accumulator1: Int, accumulator2: Int): Int =
      if (x > n) accumulator1
      else fibonacciTailRec(x+1, accumulator1 + accumulator2, accumulator1)
    if (n <= 2) 1
    fibonacciTailRec(3, 1, 1)
  }
  println(fibonacci(10))


}
