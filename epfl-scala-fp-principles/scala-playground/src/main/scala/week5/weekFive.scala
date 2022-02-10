package week5

object weekFive extends App {

  def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match
    case (Nil, ys) => ys
    case (xs, Nil) => xs
    case (x :: xs1, y :: ys1) =>
      if x < y then x :: merge(xs1, ys)
      else y :: merge(xs, ys1)


  def squareList(xs: List[Int]): List[Int] = xs match
    case Nil => Nil
    case y :: ys => y * y :: squareList(ys)

  def squareList2(xs: List[Int]): List[Int] =
    xs.map(x => x * x)

  def posElems(xs: List[Int]): List[Int] = xs match
    case Nil => xs
    case y :: ys => if y > 0 then y :: posElems(ys) else posElems(ys)


  def pack[T](xs: List[T]): List[List[T]] = xs match
    case Nil => Nil
    case x :: xs1 =>
      val (more, rest) = xs1.span( y => y == x)
      (x :: more) :: pack(rest)


  val elems = List("a","a","a","b","c","c", "a")
  pack(elems)

  def encode[T](xs: List[T]): List[(T, Int)] =
    pack(xs).map(x => (x.head, x.length))

  println(encode(elems))

  // reduction list
  def sum(xs: List[Int]): Int = (0 :: xs).foldLeft(0)(_ + _)
  def product(xs: List[Int]): Int = (1 :: xs).foldLeft(0)(_ * _)



}
