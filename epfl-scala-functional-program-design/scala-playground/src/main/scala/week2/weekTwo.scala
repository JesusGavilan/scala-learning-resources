package week2

object weekTwo extends App {
  val xs = LazyList(1,2,3)

  // LazyList ranges
  // difference between them is that elements are only
  // computed when they are needed, so when someone calls `
  // head or tail on the lazy list
  def lazyRange(lo: Int, hi: Int): LazyList[Int] =
    if lo >= hi then LazyList.empty
    else LazyList.cons(lo, lazyRange(lo + 1, hi))

  def listRange(lo: Int, hi: Int): List[Int] =
    if lo >= hi then Nil
    else lo :: listRange(lo + 1, hi)

  // x #:: xs == LazyList.cons(x, xs)
  // #:: can be used in expressions as well as patterns




}
