package problems

import scala.annotation.tailrec
object Problems {

  //1. Find the last element of a list

  @tailrec
  def last[T](ls: List[T]): T =
    ls match
      case h :: Nil => h
      case _ :: tail => last(tail)
      case _ => throw new NoSuchElementException

  //2. Find the penultimate element of the list
  @tailrec
  def penultimate[T](ls: List[T]): T =
    ls match
      case h :: _ :: Nil => h
      case _ :: tail => penultimate(tail)
      case _ => throw new NoSuchElementException

  //3. Find the nth element of the list
  @tailrec
  def nth[T](index: Int, ls: List[T]): T =
    (index, ls) match
      case (0, h :: _) => h
      case (index, _ :: tail) =>  nth(index-1, tail)
      case (_, Nil) => throw new NoSuchElementException

  //4. Find the length of a list
  def length[T](ls: List[T]): Int =
    @tailrec
    def lengthAux[T](ls: List[T], acc: Int): Int =
      ls match
        case Nil => acc
        case _ :: tail => lengthAux(tail, acc+1)
    lengthAux(ls, 0)

  //5. Reverse a list
  def reverse[T](ls: List[T]): List[T] =
    @tailrec
    def reverseAux(currentLs: List[T], reverseLs: List[T]): List[T] =
      currentLs match
        case Nil => reverseLs
        case h :: tail => reverseAux(tail, h :: reverseLs)
    reverseAux(ls, Nil)

  //6. Find out whether a list is a palindrome
  def isPalindrome[T](l: List[T]): Boolean =
    l == reverse(l)

  //7. Flatten a nested list
  def flatten(l: List[Any]): List[Any] =
    l match
      case (head: List[_]) :: tail => flatten(head) ++ flatten(tail)
      case head :: tail => head :: flatten(tail)
      case Nil => Nil

  //8. Eliminate consecutive duplicates
  def compress[T](l: List[T]): List[T] =
    @tailrec
    def compressAux(result: List[T], current: List[T]): List[T] =
      current match
        case head :: tail => compressAux(head :: result, tail.dropWhile(_ == head))
        case Nil => result.reverse
    compressAux(Nil, l)

  //9. Pack consecutive duplicates of list elements into sublist
  def pack[T](l: List[T]): List[List[T]] =
    @tailrec
    def packAux(result: List[List[T]], current: List[T]): List[List[T]] =
      current match
        case head :: tail => packAux(current.filter(item => item == head) :: result, tail.dropWhile(_ == head))
        case Nil => result.reverse

    packAux(Nil, l)

  //10. Encoding a list by run-length
  def encode[T](l: List[T]): List[(Int, T)] =
    pack(l).map(item => (item.length, item.head))

}
