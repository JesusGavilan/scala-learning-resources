package lectures.part1basics

object ValuesVariablesTypes extends App {
  // VALS ARE INMUTABLE
  val x: Int = 42
  println(x)
  // COMPILER can infer types
  val y = 45

  val aString: String = "hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short =  4564
  val aLong: Long = 456456464L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14565

  // variables
  var aVariable: Int = 4

  aVariable = 5 //side effects

 //Conclusion
  // * prefer vals over vars
  // * all vals and vars have types
  // * compiler automatically infers types when omitted




}
