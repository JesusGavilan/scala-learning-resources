package week2

import scala.annotation.tailrec

object weekTwo extends App {
  // currying
  /*
  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a + 1, b)
    sumF
  }


  def sumInts       = sum(x => x)
  def sumCubes      = sum(x => x * x * x)
  def sumFactorials = sum(fact)
   */
  def fact(n: Int): Int = {
    @tailrec
    def iter(x: Int, result: Int): Int = {
      if (x == 0) result
      else iter(x - 1, result * x)
    }
    iter(n, 1)
  }
  // previously function should be refactor as:
  def sum(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f)(a + 1, b)

  // the type of sum function?:
  // (Int => Int) => (Int, Int) => Int
  // type: ((Int, Int) => Int)

  def cube(x: Int): Int = x * x * x

  def sumInt(x: Int): Int = x + x

  // sum(cube) (1, 10) == (sum(cube)) (1,10)
  println(sum(cube)(1, 2))

  //Ex: Write a product function that calculates the product of the values of a function for the points on a given interval.
  def prod(f: Int => Int)(low: Int, high: Int): Int =
    if (high < low) 1 else f(low) * prod(f)(low + 1, high)

  println(prod(x => x * x)(1, 5))

  // More general function which generalizes both sum and product:

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    def recur(a: Int): Int =
      if (a > b) zero
      else combine(f(a), recur(a + 1))
    recur(a)
  }

  def newSum(f: Int => Int) = mapReduce(f, (x, y) => x + y, 0) _

  def newProd(f: Int => Int): (Int, Int) => Int = mapReduce(f, (x, y) => x * y, 1)

  println(newSum(fact)(1, 5))
  println(newProd(fact)(1, 5))

  def f(a: String)(b: Int)(c: Boolean): String =
    "(" + a + ", " + b + ", " + c + ")"

  val partialApplication1 = f("Scala")(2)(false)

  //val partialApplication2 = partialApplication1(42)

  //Functions an data
  class Rational(x: Int, y: Int):
    require(y > 0, s"denominator must be positive, was $x/$y")
    val numer = x
    val denom = y

    def this(x: Int) = this(x,1)

    private def gcd(a: Int, b: Int): Int =
      if b == 0 then a else gcd(b,a %b)

    def less (that: Rational): Boolean =
      numer * that.denom < that.numer * denom

    def max (that: Rational): Rational =
      if this.less(that) then that else this

    def add(r: Rational): Rational =
      Rational(numer * r.denom + r.numer * denom, denom * r.denom)

    def mul(r: Rational): Rational =
      Rational(numer * r.numer, denom * r.denom )

    def neg: Rational = Rational(-numer,denom)

    def sub(r: Rational): Rational = add(r.neg)

    override def toString = s"${numer/ gcd(x.abs,y)}/${denom/ gcd(x.abs, y)}"
  end Rational

  val x = Rational(1,3)
  val y = Rational(5,7)
  val z = Rational(3,2)

  val result = x.add(y).mul(z)
  val result2 = x.sub(y).sub(z)
  println( result2 )

  //Evaluation and operators

  extension (x: Rational)
    def + (y: Rational): Rational = x.add(y)
    def * (y: Rational): Rational = y.mul(y)

  




}
