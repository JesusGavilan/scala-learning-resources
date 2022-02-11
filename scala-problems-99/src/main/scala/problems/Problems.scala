package problems

object Problems {

  //1. Find the last element of a list

  def last[T](l: List[T]): T =
    l match
      case x :: Nil => x
      case _ :: xs => last(xs)
      case _ => throw new NoSuchElementException

  def penultimate[T](l: List[T]): T = ???

  def nth[T](index: Int, l: List[T]): T = ???

  def length[T](l: List[T]): T = ???

  def reverse[T](l: List[T]): T = ???

  def isPalindrome[T](l: List[T]): Boolean = ???

  def flatten[T](l: List[List[T]]): List[T] = ???

  def compress[T](l: List[T]): List[T] = ???

  def pack[T](l: List[T]): List[List[T]] = ???

  def encode[T](l: List[T]): List[(Int, T)] = ???

}
