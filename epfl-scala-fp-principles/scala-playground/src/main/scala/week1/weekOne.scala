package week1

import scala.annotation.tailrec

object weekOne extends App {

  def abs(x: Double)            = if (x < 0) -x else x
  def square(x: Double): Double = x * x

  def sqrt(x: Double) = {
    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))

    def isGoodEnough(guess: Double) = abs(square(guess) - x) < 0.001

    def improve(guess: Double) = (guess + x / guess) / 2

    sqrtIter(1.0)
  }

  println(sqrt(2))

  @tailrec
  def gcd(a: Int, b: Int): Int =
    if (b == 0) a
    else gcd(b, a % b)
  println(gcd(14, 21))

  def factorial(n: Int): Int = {
    @tailrec
    def iter(x: Int, result: Int): Int = {
      if (x == 0) result
      else iter(x - 1, result * x)
    }
    iter(n, 1)
  }

  println(factorial(5))

}
