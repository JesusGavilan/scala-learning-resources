package lectures.part5ts

object Variance extends App {

  trait Animal
  class Dog       extends Animal
  class Cat       extends Animal
  class Crocodile extends Animal

  // what is variance?
  // "inheritance" -> type substitution of generics

  class Cage[T]
  // yes - covariance
  class CCage[+T]
  val ccage: CCage[Animal] = new CCage[Cat]

  // no -invariance
  class ICage[T]
  // val icage: ICage[Animal]= new ICage[Cat]
  // val x: Int = "hello"

  // hell no - opposite = contravariance
  class XCage[-T]
  val xcage: XCage[Cat] = new XCage[Animal]

  class InvariantCage[T](animal: T) // invariant

}
