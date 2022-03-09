package quickcheck

import org.scalacheck.*
import Arbitrary.*
import Gen.*
import Prop.forAll

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap:
  lazy val genHeap: Gen[H] = for {
    a <- arbitrary[Int]
    h <- oneOf(const(empty), genHeap)
  } yield insert(a,h)

  given Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if isEmpty(h) then 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("min1") = forAll { (a: Int) =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("min2elements") = forAll{ (a:Int, b: Int) =>
    val min = if (a>=b) b else a
    val h1 = insert(a, empty)
    val h2 = insert(b, h1)
    findMin(h2) == min
  }

  property("min3sorted") = forAll { (h: H) =>
    def delMin(h: H, list: List[Int]): List[Int] =
      if (isEmpty(h)) list
      else findMin(h) :: delMin(deleteMin(h), list)

    val ts = delMin(h, Nil)
    ts == ts.sorted
  }

  property("meld1") = forAll{ (h1:H, h2:H) =>
    def delMin(h: H, list: List[Int]): List[Int] = {
      if (isEmpty(h)) list
      else findMin(h) :: delMin(deleteMin(h), list)
    }
    val m1 = meld(h1, h2)
    val min1 = findMin(h1)
    val m2 = meld(deleteMin(h1), insert(min1, h2))
    val l1 = delMin(m1, Nil)
    val l2 = delMin(m2, Nil)
    l1 == l2
}
