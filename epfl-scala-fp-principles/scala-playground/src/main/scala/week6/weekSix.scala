package week6

object weekSix extends App {
  val xs: Seq[Int] = (1 to 10).map(_ => 42)
  val ys: Set[Int] = xs.to(Set)
  println(xs)
  println(ys)

  // sorted and groupBy
  val fruit = List("apple", "pear", "orange", "pineapple")
  println(fruit.sortWith(_.length < _.length))
  println(fruit.sorted)
  println(fruit.groupBy(_.head))

  class Polynom(nonZeroTerms: Map[Int, Double]):

    def this(bindings: (Int, Double)*) = this(bindings.toMap)

    val terms = nonZeroTerms.withDefaultValue(0.0)

    def + (other: Polynom): Polynom =
      Polynom(other.terms.foldLeft(terms)(addTerm))

    def addTerm(terms: Map[Int,Double], term: (Int, Double)): Map[Int, Double] =
      val (exp, coeff) = term
      terms + (exp -> (terms(exp) + coeff))

    override def toString =
      val termStrings =
        for (exp, coeff) <- terms.toList.sorted.reverse
        yield
          val exponent = if exp == 0 then "" else s"x^$exp"
          s"$coeff$exponent"
      if terms.isEmpty then "0"
      else termStrings.mkString(" + ")

  val x = Polynom(0 -> 2, 1 -> -3, 2 -> 1)
  val z = Polynom()

  println( x + x + z)

  class Coder(words: List[String]):
    val mnemonics = Map(
      '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
      '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ"
    )

    // Maps a letter to the digit it represents
    private val charCode: Map[Char, Char] =
      for (digit, str) <- mnemonics; ltr <- str yield ltr -> digit

    // Maps a word to a digit string it can represent
    private def wordCode(word: String): String = word.toUpperCase.map(charCode)

    // Map a digit string to all words in the dictionary that represent it
    private val wordsForNum: Map[String, List[String]] =
      words.groupBy(wordCode).withDefaultValue(Nil)

    // All ways to encode a number as a list of words
    def encode(number: String): Set[List[String]] =
      if number.isEmpty then Set(Nil)
      else
        for
          splitPoint <- (1 to number.length).toSet
          word <- wordsForNum(number.take(splitPoint))
          rest <- encode(number.drop(splitPoint))
        yield word :: rest
  def code(number: String): Set[String] =
    val coder = Coder(List("Scala", "Python", "Ruby", "C", "rocks", "socks", "works", "pack"))
    coder.encode(number).map(_.mkString(" "))

  println( code("7225276257"))


  val word = "manotaz"
  println(word.toLowerCase.toList.groupBy(c => c).map(i => (i._1, i._2.length)).toList.sortBy(o => o._1))

  val sentence = List("hola", "me","llamo", "jesus")
  println(sentence.foldLeft("")(_ + _))

  println(sentence.foldLeft("")(_ + _))


}
