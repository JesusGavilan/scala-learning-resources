package lectures.part2oops
import scala.language.postfixOps

object MethodNotations extends App {
  class Person(val name: String, favoriteMovie: String, age: Int) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def learns(subject: String): String = s"$name learns $subject" //exercise 2
    def learnsScala(): String = this learns("Scala") //exercise 2
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie, 22) //exercise 1
    def unary_+ : Int = age + 1 //exercise 2
    def unary_! : Person = new Person(s"$name", favoriteMovie, age)
    def isAlive: Boolean = true
    def isLearning: String = learnsScala() //exercise 2
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(n: Int): String = s"$name watched $favoriteMovie $n times" // exercise 3
  }

  val mary = new Person("Mary", "Inception", 22)
  println(mary.likes("Inception"))
  println(mary likes "Inception") // infix notation or operator notation (syntactic sugar)

  //operators in Scala
  val tom  = new Person("Tom", "Fight Club", 23)
  println(mary + tom)
  println(mary.+(tom))

  // ALL OPERATORS ARE METHODS IN SCALA
  println( 1 + 2)
  println( 1.+(2))

  //prefix notation (another form of syntactic sugar
  val  x = -1 // equivalent with 1.unary_-
  val y = 1.unary_- //unary operators
  // unary_prefix only works with - + ~ ! operators
  println(!mary)
  println(mary.unary_!)

  //postfix notation --> methods without parameters
  println(mary.isAlive) //equivalents
  println(mary  isAlive)

  //apply
  println(mary.apply()) //equivalents
  println(mary())

  //Exercise 1
  /*
    1. Overload the + operators
      mary + "the rockstar" => new person "Mary (the rockstar)"
    */
  println((mary + "the rockstar")())

  /*
    2. Add an age to the Person class
      Add a unary + operator => new person with the age +1
      +mary => mary with the age incrementer
  */
  println(s"Age of Mary incrementing: ${+mary}")
  /*
    3. Add a 'learns' method in the Person class  => "Mary learns Scala"
      Add a learnsScala method, calls learns method with "Scala"
      Use it in postfix notation
  */
  println(mary isLearning)
  /*
    4. Overload the apply method
      mary.apply(2) => "Mary watched Inception 2 times"
   */
  println(mary(3))

}
