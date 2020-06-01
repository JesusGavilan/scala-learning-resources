package lectures.part2oops

import java.time.Year

object OOBasics extends App {
  //person examples
  val person = new Person(name="Jesus", age=32)
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet()
  val second_person = new Person("Abel")
  second_person.greet()
  val third_person = new Person()
  third_person.greet()
  //exercise 1
  val author = new Writer(firstname = "Jesus", surname = "Gavilan", year = 1987)
  val impostor = new Writer("Jesus", "Gavilan", 1987)
  println(s"Author fullname: ${author.fullname()}")

  val novel = new Novel(name = "OKIS", yearOfRelease = 2020, author = author)
  println(s"Novel author age: ${novel.authorAge}")
  println(novel.isWrittenBy(impostor))
  val newYOR = novel.copy(2021).yearOfRelease.toString
  println(s"New year of release novel: $newYOR")

  //exercise2
  val counter = new Counter(count = 5)
  counter.print
  counter.inc.inc.inc.print
  counter.dec.print
  counter.inc(15).print
  counter.dec(7).print
}
// constructor
class Person(name: String, val age: Int = 0)  {
  //body
  val x = 2
  println(1+3)

  //method
  def greet(name: String): Unit = println(s"${this.name} says: hi, $name")

  //ovearloading
  def greet(): Unit = println(s"Hi, I am $name")

  //multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}

// class parameters are NOT FIELDS, require val

//Exercise 1
/*
  Novel and a writer

  Writer: first name, surname, year
    - method fullname
  Novel: name, year of realease, author
    - authorAge
    - isWrittenBy (author)
    - copy (new year of release) = new instance of Novel.
*/
class Writer(firstname: String, surname: String, val year: Int) {
  def fullname(): String = s"$firstname $surname"
}

class Novel(name: String, val yearOfRelease: Int, author: Writer) {
  def authorAge(): Int = Year.now.getValue - author.year
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newYOR : Int): Novel = new Novel(name, newYOR, author)
}

//Exercise 2
/*
  Counter class
  - receives an int value
  - method current count
  - method to increment/decerment => new Counter
  - overload inc/dec to receive an amount
*/
class Counter(val count : Int = 0) {
  def inc = new Counter(count + 1) //inmutability
  def dec = new Counter(count - 1)

  def inc(amount : Int): Counter = {
    if (amount <= 0) this
    else inc.inc(amount-1) // inc(5) = inc.inc.inc.inc.inc
  }
  def dec(amount : Int) : Counter = {
    if (amount <= 0) this
    else dec.dec(amount-1)
  }
  def print() = println(count)
}

