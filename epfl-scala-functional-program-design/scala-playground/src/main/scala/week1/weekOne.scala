package week1

import scala.language.postfixOps

object weekOne extends App {

  // Representation of JSON with enums
  enum JSON:
    case Seq(elems: List[JSON])
    case Obj (bindings: Map[String, JSON])
    case Num (num: Double)
    case Str (str: String)
    case Bool(b: Boolean)
    case Null

  // returns string representation of JSON data
  def show(json: JSON): String = json match
    case JSON.Seq(elems) => elems.map(show).mkString("[",",","]")
    case JSON.Obj(bindings) =>
      val assocs = bindings.map(
        (key, value) => s"${inQuotes(key)}: ${show(value)}")
      assocs.mkString("{",",\n","}")
    case JSON.Num(num) => num.toString
    case JSON.Str(str) => inQuotes(str)
    case JSON.Bool(b) => b.toString
    case JSON.Null => "null"


  def inQuotes(str: String): String = "\"" + str +"\""

  val jsData = JSON.Obj(Map(
    "firstName" -> JSON.Str("Joan"),
    "lastName" -> JSON.Str("Smith"),
    "address" -> JSON.Obj(Map(
      "streetAddress" -> JSON.Str("32 Fake Street"),
      "state" -> JSON.Str("NY"),
      "postalCode" -> JSON.Num(1251)
    )),
    "phoneNumbers" -> JSON.Seq(List(
      JSON.Obj(Map(
        "type" -> JSON.Str("home"),
        "number" -> JSON.Str("4556456465")
      )),
      JSON.Obj(Map(
        "type" -> JSON.Str("fax"),
        "number" -> JSON.Str("4454545656")
      ))
    ))
  ))

  println(show(jsData))

  // FOR could be used as a query for a database
  case class Book(title: String, authors: List[String])

  val books: List[Book] = List(
    Book(title = "A good book", authors = List("Payne, John", "Smith, Alan")),
    Book(title = "A bad book", authors = List("Pad, Tea", "Slim, Joseph")),
    Book(title = "A simple book", authors = List("Dore, Alfred", "Athero, Emanuel")),
    Book(title = "A mediocre book", authors = List("Hummel, Trim", "Bendt, Louis")),
    Book(title = "Another book", authors = List("Spring, Mark", "Bendt, Louis")),
    Book(title = "A blue book", authors = List("Salcedo, Daniel", "Kasabian, Tom"))
  )

  // look for a title of books whose authors name is "Slim"
  val search =
    for
      b <- books
      a <- b.authors
      if a.startsWith("Slim")
    yield b.title
  println(search)

  // find the names of all authors who have written at least 2 books
  val search2 =
    for
      b1 <- books
      b2 <- books
      if b1.title < b2.title
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    yield a1

  println(search2.distinct)

  //----------------
  // generators
  //----------------
  trait Generator[+T]:
    def generate(): T

    def map[S](f: T => S) = new Generator[S]:
      def generate() = f(Generator.this.generate())

    def flatMap[S](f: T => Generator[S]) = new Generator[S]:
      def generate() = f(Generator.this.generate()).generate()


  val integers = new Generator[Int]:
    val rand = java.util.Random()
    def generate() = rand.nextInt()

  val booleans = new Generator[Boolean]:
    def generate() = integers.generate() > 0

  val pairs = new Generator[(Int, Int)]:
    def generate() = (integers.generate(), integers.generate())

  def single[T](x:T): Generator[T] = new Generator[T]:
    def generate() = x

  def range(lo: Int, hi: Int): Generator[Int] =
    for x <- integers yield lo + x.abs % (hi -lo)

  def oneOf[T](xs: T*): Generator[T] =
    for idx <- range(0, xs.length) yield xs(idx)

  def lists: Generator[List[Int]] =
    for
      kind <- range(0,5)
      list <- if kind == 0 then emptyLists else nonEmptyLists
    yield list

  def emptyLists = single(Nil)
  def nonEmptyLists =
    for
      head <- integers
      tail <- lists
    yield head :: tail


  println(pairs.generate())
  val choice = oneOf("red", "green", "blue")
  println(choice.generate())
  println(lists.generate())


  enum Tree:
    case Inner(left: Tree, right: Tree)
    case Leaf(x: Int)

  def trees: Generator[Tree] =
    for
      isLeaf <- booleans
      tree <- if isLeaf then leafs else inners
    yield
      tree
  def leafs = for x <- integers yield Tree.Leaf(x)
  def inners = for x <- trees; y <- trees yield Tree.Inner(x,y)

  println(trees.generate())
}
