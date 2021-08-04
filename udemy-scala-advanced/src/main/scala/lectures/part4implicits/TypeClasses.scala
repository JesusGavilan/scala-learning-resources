package lectures.part4implicits

object TypeClasses extends App {

  trait HTMLWritable {
    def toHtml: String
  }

  case class User(name: String, age: Int, email: String) extends HTMLWritable {
    override def toHtml: String = s"<div>$name ($name yo) <a href=$email/> </div>"
  }

  User("John", 32, "john@rockthejvm.com").toHtml
  /* Disadventages
   1- for the type WE write
   2- ONe implemetation out of quite a number
   */

  // option 2 -pattern matching
  object HTMLSerializerPm {
    def serrializeToHtml(value: Any) = value match {
      case User(n, a, e) =>
      case _             =>
    }
  }
  /* 1- lost type safety
  2 -neet to modify the code every time
  3 - still ONE implementation
   */

  trait HTMLSerializer[T] {
    def serialize(value: T): String
  }

  implicit object UserSerializer extends HTMLSerializer[User] {
    def serialize(user: User): String = s"<div>${user.name} (${user.age} yo) <a href=${user.email}/> </div>"
  }
  val john = User("John", 32, "john@rockthejvm.com")
  println(UserSerializer.serialize(john))
  //1 - we can define serializers for other types
  import java.util.Date
  object DateSerializer extends HTMLSerializer[Date] {
    override def serialize(date: Date): String = s"<div>${date.toString()}</div>"
  }

  //2 - we can define MULTIPLE serializers
  object PartialUserSerializer extends HTMLSerializer[User] {
    def serialize(user: User): String = s"<div>${user.name}</div>"
  }

  // TYPE CLASS

  trait MyTypeClassTemplate[T] {
    def action(value: T): String
  }

  object MyTypeClassTemplate {
    def apply[T](implicit instance: MyTypeClassTemplate[T]) = instance
  }

  /**
    * Equality  type class
    */
  /*trait Equal[T] {
    def compare(valueA: T, valueB: T): Boolean
  }
  object NameComparator extends Equal[User] {
    def compare(userA: User, userB: User): Boolean = userA.name.equals(userB.name)
  }

  object NameAndEmailComparator extends Equal[User] {
    def compare(userA: User, userB: User): Boolean = userA.name.equals(userB.name) && userA.email.equals(userB.email)
  }*/
  trait Equal[T] {
    def compare(valueA: T, valueB: T): Boolean
  }
  object Equal {
    def apply[T](valueA: T, valueB: T)(implicit equalizer: Equal[T]): Boolean =
      equalizer.compare(valueA, valueB)
  }

  implicit object NameComparator extends Equal[User] {
    def compare(userA: User, userB: User): Boolean = userA.name.equals(userB.name)
  }

  object NameAndEmailComparator extends Equal[User] {
    def compare(userA: User, userB: User): Boolean = userA.name.equals(userB.name) && userA.email.equals(userB.email)
  }

  val userOne = User("jesus", 33, "jesus@xd.com")
  val userTwo = User("jesus", 21, "jesus@xd.com")
  println(NameComparator.compare(userOne, userTwo))

  // part 2
  object HTMLSerializer {
    def serialize[T](value: T)(implicit serializer: HTMLSerializer[T]): String =
      serializer.serialize(value)
    def apply[T](implicit serializer: HTMLSerializer[T]) = serializer

  }

  implicit object IntSerializer extends HTMLSerializer[Int] {
    override def serialize(value: Int): String = s"<div style: color=blue>$value</div>"
  }

  println(HTMLSerializer.serialize(42))
  println(HTMLSerializer.serialize(john))
  // access to the entire type class interface
  println(HTMLSerializer[User].serialize(john))

  /*
   Exercise: implement the TC for the Eqquality TC
   */
  val anotherJohn = User("John", 45, "anotherjesus@xd.com")
  println(Equal.apply(john, anotherJohn))

  // AD-HOC polymorphism
}