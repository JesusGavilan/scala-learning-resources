package exercises

object HOF extends App {

  //-- sum of integers between a and b
  def sumInts(a: Int, b: Int): Int =
    if (a > b) 0 else a + sumInts(a + 1, b)

  println("Sum from 2 to 4: "+sumInts(2, 4)) //9

  //-- sum of all cubes between a and b
  def cube(x: Int): Int = x * x * x

  def sumCubes(a: Int, b: Int): Int =
    if (a > b) 0 else cube(a) + sumCubes(a + 1, b)

  println("Sum cubes from 2 to 4: " + sumCubes(2, 4))

  //-- sum of the factorials between a and b
  def factorial(n: Int): Int =
    if (n == 0) 1 else n*factorial(n-1)

  def sumFactorials(a: Int, b: Int): Int =
    if (a > b) 0 else factorial(a) + sumFactorials(a + 1, b)

  println("Sum factorials from 2 to 4: " + sumFactorials(2,4))

  // let's factoring ot the common pattern:

  def sum(f: Int => Int, a: Int, b: Int): Int =
    if(a > b) 0
    else f(a) + sum(f, a + 1, b )

  // So, we could write:
  def id(x: Int): Int = x //identity function
  def sumIntsHOF(a: Int, b: Int) = sum(id, a, b)
  def sumCubesHOF(a: Int, b: Int) = sum(cube, a, b)
  def sumFactorialsHOF(a: Int, b: Int) = sum(factorial, a, b)

  println("Sum HOF from 2 to 4: " + sumIntsHOF(2,4))
  println("Sum cubes HOF from 2 to 4: " + sumCubesHOF(2,4))
  println("Sum factorial HOF from 2 to 4: " +  sumFactorialsHOF(2,4))

}
