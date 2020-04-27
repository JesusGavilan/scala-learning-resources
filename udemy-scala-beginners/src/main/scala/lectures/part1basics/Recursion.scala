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


  //2. IsPrime function tail recursive


  //3. Fibonacci function, tail recursive



}
