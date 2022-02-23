package problems
import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuite

class ProblemsSpec extends AnyFunSuite with BeforeAndAfter :
  import Problems.*

  val intList = List(1, 3, 5, 6, 7, 8, -9)
  val intListPalindrome = List(1,3,5,7,5,3,1)
  val intListOfInts = List(List(1,3,5), List(6,7), List(8,-9))
  val strList = List("my", "name", "is", "Jonas")
  val strListRepeated = List("m","m","m","t","o","o","o","y","p","p","p","p","a")
  val tupleList = List(("city", "Barcelona"), ("name", "Albert"), ("age", "43"))

  test("p01: testing the last function") {
    assert(last(intList) == -9)
    assert(last(strList) === "Jonas")
    assert(last(tupleList) === ("age", "43"))
  }

  test("p02: testing the penultimate function") {
    assert(penultimate(intList) == 8)
    assert(penultimate(strList) === "is")
    assert(penultimate(tupleList) === ("name", "Albert"))
}
  test("p03: testing the nth function") {
    assert(nth(4, intList) === 7)
    assert(nth(0, strList) === "my")
    assert(nth(1, tupleList) === ("name", "Albert"))
  }
  test("p04: testing length function") {
    assert(length(intList) === 7)
    assert(length(strList) === 4)
    assert(length(tupleList) === 3)
  }
  test("p05: testing reverse function") {
    assert(reverse(intList) === intList.reverse)
    assert(reverse(strList) === strList.reverse)
    assert(reverse(tupleList) === tupleList.reverse)
  }
  test("p06: testing isPalindrome function") {
    assert(isPalindrome(intList) === false)
    assert(isPalindrome(strList) === false)
    assert(isPalindrome(tupleList) === false)
    assert(isPalindrome(intListPalindrome) === true)
  }
  test("p07: testing flatten function") {
    assert(flatten(intListOfInts) === intList)
  }
  test("p08: testing compress function") {
    assert(compress(strListRepeated) === List("m","t","o","y","p","a"))
  }
  test("p09: testing pack function") {
    assert(pack(strListRepeated) === List(List("m","m","m"), List("t"), List("o","o","o"), List("y"), List("p","p","p","p"), List("a")))
  }
  test("p10: testing encode function") {
    assert(encode(strListRepeated) === List((3,"m"),(1, "t"),(3,"o"),(1,"y"),(4,"p"),(1,"a")))
  }
  test("p11: testing encode updated function") {
    assert(encodeUpdated(strListRepeated) === List((3,"m"),(3,"o"),(4,"p")))
  }
  test("p12: testing decode function") {
    assert(decode( List((3,"m"),(1, "t"),(3,"o"),(1,"y"),(4,"p"),(1,"a"))) === strListRepeated)
  }
  test("p13: testing encoding direct function") {
    assert(encodeDirect(strListRepeated) === List((3,"m"),(1, "t"),(3,"o"),(1,"y"),(4,"p"),(1,"a")))
  }
  test("p14: testing duplicate function") {
    assert(duplicate(strListRepeated) === List("m","m","m","m","m","m","t","t","o","o","o","o","o","o","y","y","p","p","p","p","p","p","p","p","a","a"))
  }
  test("p15: testing duplicate n elements function") {
    assert(duplicateN(2, intList) === List(1,1, 3,3, 5, 5, 6, 6, 7, 7, 8, 8, -9, -9))
  }
  test("p16: testing dropN function") {
    assert(drop(3, strListRepeated) === List("m","m","t","o","o","y","p","p","a"))
  }
  test("p17: testing split function") {
    assert(split(3, strListRepeated) === List(List("m","m","m"),List("t","o","o"),List("o","y","p"),List("p","p","p"),List("a")))
  }
  test("p18: testing slice function") {
    assert(slice(5,9, strListRepeated) === List("o","o","y"))
  }
  test("p19: testing rotate left function") {
    assert(rotate(5, strListRepeated) === List("o","o","y","p","p","p","p","a","m","m","m","t","o"))
  }
  test("p20: testing remove the kth element of a list function") {
    assert(removeAt(4, strListRepeated) === List(1, 3, 5, 6, 8, -9))
  }



