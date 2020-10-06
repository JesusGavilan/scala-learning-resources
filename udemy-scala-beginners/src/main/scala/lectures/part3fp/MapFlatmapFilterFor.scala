package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1,2,3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")

  // iterating
  val combinations = numbers.flatMap(number => chars.flatMap(char => colors.map(color => ""+ char + number + "-" + color )))
  println(combinations)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    number <- numbers
    char <- chars
    color <- colors
  } yield "" + char + number + "-" + color

  println(forCombinations)

  /*
   Exercises:
   1. MyList supports for comprehensions?
      YES: In order to allow for comprehensions
      the map, filter and filterMap should have
      the following signatures:
      map(f: A => B) => MyList[B]
      filter(p: A => Boolean) => MyList[A]
      flatMap(f: A => MyList[B]) => MyList[B]
   2. A small collection of at most ONE element - Maybe [+T]
      - map, flatMap, filter
   */
}
