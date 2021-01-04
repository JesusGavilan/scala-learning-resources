package lectures.part4collections

object operations extends App {
  // 4.1 calculating the standard deviation of an array using scalla collection operations
  def stdDev(a: Array[Double]): Double = {
    val mean = a.sum / a.length
    val squareErrors = a.map(x => x - mean).map(x => x * x)
    math.sqrt(squareErrors.sum/ a.length)
  }

  println("4.1 Standard deviation of 1,2,3,4,5,6,7,8,9,10: \n" + stdDev(Array(1,2,3,4,5,6,7,8,9,10)))

  //4.1.1 builders
  // for construct efficiently a collection of unknow length
  val b = Array.newBuilder[Int]
  b += 1
  b += 2
  println(b.result().toSeq)

  //4.1.2 factory methods
  println(Array.fill(5)("hello").toSeq) // array with hello repeated 5 tyimes
  println(Array.tabulate(5)(n => s"Hello $n").toSeq) // array with 5 items, each computed from its index
  println((Array(1,2,3) ++ Array(4,5,6)).toSeq) // concatenating

  //4.1.4 Queries
  println(Array(1,2,3,4,5,6,7,8).find(i => i %2 == 0 && i > 4))

  //4.1.5 aggregations
  println(Array(1,2,3,4,5,5).mkString(","))

  // foldleft
  println(Array(1,2,3,4,5,6,7,8,9,10).foldLeft(0)(_ +  _))

  // checking incoming grid of numbers is a valid Sudoko grid
  def isValidSudoku(grid: Array[Array[Int]]): Boolean = {
    !Range(0,9).exists{i =>
      val row = Range(0,9).map(grid(i)(_))
      val col = Range(0,9).map(grid(_)(i))
      val square = Range(0,9).map(j => grid((i%3) * 3 + j %3)((i/3)*3 + j /3))
      row.distinct.length != row.length ||
      col.distinct.length != col.length ||
      square.distinct.length != square.length
    }
  }
  println(isValidSudoku(Array(
    Array(5, 3, 4, 6, 7, 8, 9, 1, 2),
    Array(6, 7, 2, 1, 9, 5, 3, 4, 8),
    Array(1, 9, 8, 3, 4, 2, 5, 6, 7),
    Array(8, 5, 9, 7, 6, 1, 4, 2, 3),
    Array(4, 2, 6, 8, 5, 3, 7, 9, 1),
    Array(7, 1, 3, 9, 2, 4, 8, 5, 6),
    Array(9, 6, 1, 5, 3, 7, 2, 8, 4),
    Array(2, 8, 7, 4, 1, 9, 6, 3, 5),
    Array(3, 4, 5, 2, 8, 6, 1, 7, 9)
  )))

  println(isValidSudoku(Array(
    Array(5, 3, 4, 6, 7, 8, 9, 1, 2),
    Array(6, 7, 2, 1, 9, 5, 3, 4, 8),
    Array(1, 9, 8, 3, 4, 2, 5, 6, 7),
    Array(8, 5, 9, 7, 6, 1, 4, 2, 3),
    Array(4, 2, 6, 8, 5, 3, 7, 9, 1),
    Array(7, 1, 3, 9, 2, 4, 8, 5, 6),
    Array(9, 6, 1, 5, 3, 7, 2, 8, 4),
    Array(2, 8, 7, 4, 1, 9, 6, 3, 5),
    Array(3, 4, 5, 2, 8, 6, 1, 7, 8)
  )))
//4.4 common interfaces
  def iterateOverSomething[T](items: Seq[T]) = {
    for (i <- items) println(i)
  }
  iterateOverSomething(Vector(1,2))
  iterateOverSomething(List(1,2))
}
