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


  def isPalindrome[T](l: List[T]): Boolean = ???

  def flatten[T](l: List[List[T]]): List[T] = ???

  def compress[T](l: List[T]): List[T] = ???

  def pack[T](l: List[T]): List[List[T]] = ???

  def encode[T](l: List[T]): List[(Int, T)] = ???

}
