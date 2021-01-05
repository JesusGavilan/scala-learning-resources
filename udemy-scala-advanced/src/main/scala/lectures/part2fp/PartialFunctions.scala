package lectures.part2fp

object PartialFunctions extends App {
  val aFunction = (x: Int) => x +1 // Function1[Int, Int] === Int => Int

  val aFussyFunction = (x: Int) =>
    if(x == 1) 42
    else if ( x == 2) 999
    else throw new FunctionNotApplicableException

  class FunctionNotApplicableException extends RuntimeException

  val aNicerFussyFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  }
  // {1,2,5} => Int
  // equivalent
  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  } // partial function value
  println(aPartialFunction(2))
  //println(aPartialFunction(655))

  // PF utilities
  println(aPartialFunction.isDefinedAt(67)) // for testing if a PF applies for this arg
  //lift
  val lifted = aPartialFunction.lift // Int => Option[Int] for transforming a PF to a total function
  println(lifted(2))
  println(lifted(98))
  // chaining PF
  val pfChain = aPartialFunction.orElse[Int, Int] {
    case 45 => 67
  }

  println(pfChain(2))
  println(pfChain(45))

  // PF extend normal functions
  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  // HOFs accept partial functions as well
  val aMappedList = List(1,2,3).map {
    case 1 => 42
    case 2 => 78
    case 3 => 1000
  }
  println(aMappedList)
  /*
   Note: PF can only have ONE parameter type
   */

  /**
   *  Exercise
   *  1. Construct a PF instance yourself (anonymous class)
   */
  val aManualFussyFunction = new PartialFunction[Int, Int] {
    override def apply(x: Int): Int = x match {
      case 1 => 42
      case 2 => 65
      case 5 => 999
    }

    override def isDefinedAt(x: Int): Boolean =
      x == 1 || x ==2 || x ==5
  }

  println(aManualFussyFunction(2))
  /**
   * 2. dumb chatbot as a PF
   */
  val aPFChat: PartialFunction[String, String] = {
    case "hola" => s"hola!, que tal?"
    case "adios" => s"Hasta la vista!"
  }
  scala.io.Source.stdin.getLines().map(aPFChat).foreach(println)
}
