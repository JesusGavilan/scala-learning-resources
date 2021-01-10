package exercises.extra

import exercises.extra.MyLinkedList._

import scala.annotation.tailrec


sealed trait MyLinkedList[+A]
case object  Nil extends MyLinkedList[Nothing]
case class Cons[+A](head: A, tail: MyLinkedList[A]) extends MyLinkedList[A]

object MyLinkedList {
  def sum(ints: MyLinkedList[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: MyLinkedList[Double]): Double = ds match {
    case Nil => 0
    case Cons(x, xs) => x * product(xs)
  }

  def tail[A](myList: MyLinkedList[A]): MyLinkedList[A]= myList match {
    case Nil => myList
    case Cons(_, xs) => xs
  }

  @tailrec
  def drop[A](myList: MyLinkedList[A], numElemDrop: Int): MyLinkedList[A] = myList match {
    case Nil => myList
    case Cons(_, xs) =>
      if (numElemDrop == 0) myList
      else drop(xs, numElemDrop-1)
  }

  def dropWhile[A](myList: MyLinkedList[A], f: A => Boolean): MyLinkedList[A] = myList match {
    case Cons(x, xs) if f(x) => dropWhile(xs, f)
    case _ => myList
  }

  def append[A](myList: MyLinkedList[A], anotherList: MyLinkedList[A]): MyLinkedList[A] = myList match {
    case Nil => anotherList
    case Cons(x, xs) => Cons(x, append(xs, anotherList))
  }

  def init[A](myList: MyLinkedList[A]): MyLinkedList[A] = myList match {
    case Nil => Nil
    case Cons(_, Nil) => Nil
    case Cons(x, xs) => Cons(x, init(xs))
  }

  def setHead[A](myList: MyLinkedList[A], newHead: A): MyLinkedList[A] = myList match {
    case Nil => Cons(newHead, Nil)
    case Cons(_, xs) => Cons(newHead, xs)
  }
 // Variadic function --> accepts zero or more arguments of Type A. For passing a Seq of elements explicity
  def apply[A](as: A*): MyLinkedList[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}

object playground extends App {
  import MyLinkedList.sum
  val ex1 = MyLinkedList(1,2,3) match { case _ => 42}
  println(ex1)
  val ex2 = MyLinkedList(1,2,3) match { case Cons(h, _) => h}
  println(ex2)

  val ex3 = MyLinkedList(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons (x, Cons(y, Cons(3, Cons(4,_)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }
  println(ex3)

  val ex4 = tail(MyLinkedList(1, 2, 3))
  println(ex4)

  val ex5 = setHead(MyLinkedList(1,2,3), 10)
  val ex5_1 = setHead(Nil, 3)
  println(ex5_1)

  val ex6 = drop(MyLinkedList(1,2,3,4,5), 2)
  println(ex6)

  val ex7 = dropWhile(MyLinkedList(2,2,2,4,5,6), (x: Int) => x % 2 == 0)
  println(ex7)

  val ex8 = append(MyLinkedList(1, 2, 3), MyLinkedList(12))
  println(ex8)

  val ex9 = init(MyLinkedList(1,2,3,4,5,6,7,8,9,10))
  println(ex9)
}
