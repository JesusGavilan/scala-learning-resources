package lectures.part4implicits

import lectures.part4implicits.TypeClasses.User

object EqualityPlayground extends App {

  trait Equal[T] {
    def compare(valueA: T, valueB: T): Boolean
  }

  implicit object NameComparator extends Equal[User] {
    def compare(userA: User, userB: User): Boolean = userA.name.equals(userB.name)
  }

  object NameAndEmailComparator extends Equal[User] {
    def compare(userA: User, userB: User): Boolean = userA.name.equals(userB.name) && userA.email.equals(userB.email)
  }

  /*
   Exercise: implement the Type Class  for the Equality TC
   */

  object Equal {
    def apply[T](valueA: T, valueB: T)(implicit equalizer: Equal[T]): Boolean =
      equalizer.compare(valueA, valueB)
  }
  val john = User("John", 32, "john@rockthejvm.com")

  val anotherJohn = User("John", 45, "anotherjesus@xd.com")
  println(Equal(john, anotherJohn)) // AD-HOC polymorphism

  /*
   Exercise - improve the Equal TC with an implicit conversion class
    -  === (anotherValue: T)
    -  !== (anotherValue: T
   */

  implicit class TypeSafeEqual[T](value: T) {
    def ===(other: T)(implicit equalizer: Equal[T]): Boolean = equalizer.compare(value, other)
    def !==(other: T)(implicit equalizer: Equal[T]): Boolean = !equalizer.compare(value, other)
  }

  println(john === anotherJohn)
  /*
    john.===(anotherJohn)
    new TypeSafeEqual[User](john).===(anotherJohn)
    new TypeSafeEqual[User](john).===(anotherJohn)(NameComparator)
   */
  /*
    TYPE SAFE
   */
  //println( john === 43) // TYPE SAFE

}
