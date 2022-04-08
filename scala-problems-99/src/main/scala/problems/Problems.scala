package problems

import scala.annotation.tailrec
import scala.reflect.ClassManifestFactory.{Any, Nothing}
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
      case (index, _ :: tail) => nth(index - 1, tail)
      case (_, Nil) => throw new NoSuchElementException

  //4. Find the length of a list
  def length[T](ls: List[T]): Int =
    @tailrec
    def lengthAux[T](ls: List[T], acc: Int): Int =
      ls match
        case Nil => acc
        case _ :: tail => lengthAux(tail, acc + 1)

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

  //11. Updated encoding a list by run-length
  def encodeUpdated[T](l: List[T]): List[(Int, T)] =
    encode(l).filter(item => item._1 > 1)

  //12. Decoding a encoded list by run-length
  def decode[T](l: List[(Int, T)]): List[T] =
    l.flatMap(item => List.fill(item._1)(item._2))


  //13. Encoding a list by run-length, direct solution
  def encodeDirect[T](l: List[T]): List[(Int, T)] =
    @tailrec
    def encodeAux(result: List[(Int, T)], current: List[T]): List[(Int, T)] =
      current match
        case head :: tail => encodeAux((current.count(item => item == head), head) :: result, tail.dropWhile(_ == head))
        case Nil => result.reverse

    encodeAux(Nil, l)


  //14. Duplicate elements of a list
  def duplicate[T](l: List[T]): List[T] =
    l match
      case head :: tail => head :: head :: duplicate(tail)
      case Nil => Nil


  //15. Duplicate elements of a list N number of times
  def duplicateN[T](n: Int, l: List[T]): List[T] =
    l match
      case head :: tail => List.fill(n)(head) ++ duplicateN(n, tail)
      case Nil => Nil

  //16. Drop every nth element of a list
  def drop[T](n: Int, l: List[T]): List[T] =
    @tailrec
    def dropAux(inc: Int, current: List[T], result: List[T]): List[T] =
      current match
        case head :: tail =>
          if (inc == n) dropAux(1, tail, result)
          else dropAux(inc + 1, tail, head :: result)
        case Nil => result.reverse

    dropAux(1, l, Nil)

  //17. Split a list in two parts
  def split[T](n: Int, l: List[T]): (List[T], List[T]) =
    def splitAux(inc: Int, currentList: List[T], currentHead: List[T]): (List[T], List[T]) =
      (inc, currentList) match
        case (_, Nil) => (currentHead.reverse, Nil)
        case (valInc, head :: tail) => {
          if (valInc < n) splitAux(inc + 1, tail, head :: currentHead)
          else (currentHead.reverse, head :: tail)
        }

    splitAux(0, l, Nil)


  //18. Extract a slice from a list
  def slice[T](i: Int, k: Int, l: List[T]): List[T] =
    @tailrec
    def sliceAux(inc: Int, result: List[T], currentList: List[T]): List[T] =
      (result, currentList) match
        case (res, Nil) => res.reverse
        case (res, head :: tail) =>
          if (inc > i && inc < k) sliceAux(inc + 1, head :: res, tail)
          else sliceAux(inc + 1, res, tail)

    sliceAux(1, Nil, l)

  //19. Rotate a list n places to the left
  def rotate[T](n: Int, l: List[T]): List[T] =
    val nbis = if (l.isEmpty) 0 else n % l.length
    if (nbis < 0) rotate(l.length + n, l)
    else l.drop(nbis) ::: l.take(nbis)

  //20. Remove the kth element from a list
  def removeAt[T](k: Int, l: List[T]): (List[T], T) =
    if (k < 0) throw new NoSuchElementException
    else (k, l) match {
      case (_, Nil) => throw new NoSuchElementException
      case (0, head :: tail) => (tail, head)
      case (_, head :: tail) =>
        val (t, e) = removeAt(k - 1, l.tail)
        (l.head :: t, e)
    }
  
  //21. Insert an element at a given position into a list
  def insertAt[T](v: T, i: Int, l: List[T]): List[T] = ???

  //22. Create a list containing all integers within a given range
  def range[T](start: Int, end: Int): List[Int] = ???

  //23. Extract a given number of randomly selected elements from a list
  def random[T](v: Int, l: List[T]): List[T] = ???

  //24. Draw N different random numbers from the set 1..M.
  def lotto(n: Int, l: List[Int]): List[Int] = ???

  //25. Generate a random permutation of the elements of a list
  def randomPermute[T](l: List[T]): List[T] = ???

  //26. Generate the combinations of K distinct objects chosen from the N elements of a list
  def combinations[T](k: Int, l: List[T]): List[List[T]] = ???

  //27. Group the elements of a set into disjoint subsets
  def group[T](groupSize: List[Int], l: List[T]): List[List[List[T]]] = ???

  //28. Sorting a list of lists according to length of sublists
  def lsort[T](l: List[List[T]]): List[List[T]] = ???
}
