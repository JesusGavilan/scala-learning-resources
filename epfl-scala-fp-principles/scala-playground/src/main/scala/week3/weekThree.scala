package week3

object weekThree extends App {

  abstract class IntSet:
    def incl(x: Int): IntSet
    def contains(x: Int): Boolean
    def union(other: IntSet): IntSet

  object IntSet:
    def apply(): IntSet = Empty
    def apply(x: Int): IntSet = Empty.incl(x)
    def apply(x: Int, y: Int): IntSet = Empty.incl(x).incl(y)

  object Empty extends IntSet:
    def contains(x: Int): Boolean = false
    def incl(x: Int): IntSet = NonEmpty(x, Empty, Empty)
    def union(s: IntSet): IntSet = s

  class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet:
    def contains(x: Int): Boolean =
      if x < elem then left.contains(x)
      else if x > elem then right.contains(x)
      else true

    def incl(x: Int): IntSet =
      if x < elem then NonEmpty(elem, left.incl(x), right)
      else if x > elem then NonEmpty(elem, left, right.incl(x))
      else this

    def union(s: IntSet): IntSet =
      left.union(right).union(s).incl(elem)


  val arbol = NonEmpty(2, NonEmpty(1, Empty, Empty), NonEmpty(3, Empty, Empty))

  println(arbol.contains(0))

  trait LIST[T]:
    def isEmpty: Boolean
    def head: T
    def tail: LIST[T]


  class CONS[T](val head: T, val tail: LIST[T]) extends LIST[T]:
    def isEmpty = false


  class NIL[T] extends LIST[T]:
    def isEmpty = true
    def head = throw new NoSuchElementException("Nil.head")
    def tail = throw new NoSuchElementException("Nil.tail")

  def Singleton[T](elem: T) = CONS(elem, new NIL[T])

  //example: Write a function nth that takes a list and an integer n and selects the n'th element of the list
  def nth[T](xs: LIST[T], n: Int): T =
    if xs.isEmpty then throw IndexOutOfBoundsException()
    else if n == 0 then xs.head
    else nth(xs.tail, n-1)


  println(nth(CONS(1, CONS(2, CONS(3, NIL()))),2))

  // provide an implementation of the abstract class Nat that represents non-negative integers
  abstract class Nat:
    def isZero: Boolean
    def predecessor: Nat
    def successor: Nat
    def + (that: Nat): Nat
    def - (that: Nat): Nat

  object Zero extends Nat:
    def isZero: Boolean = true
    def predecessor: Nat = throw new IndexOutOfBoundsException()
    def successor: Nat = Succ(this)
    def + (that: Nat) = that
    def - (that: Nat) = if that.isZero then this else ???
    override def toString = "Zero"

  class Succ(n: Nat) extends Nat:
    def isZero = false
    def predecessor: Nat = n
    def successor: Nat = Succ(this)
    def + (that: Nat): Nat = Succ(n + that)
    def - (that: Nat): Nat = if that.isZero then this else n - that.predecessor
    override def toString = s"Succ($n)"

  val two = Succ(Succ(Zero))
  val one = Succ(Zero)

  println(two + one)
  println(two - one)
  



}
