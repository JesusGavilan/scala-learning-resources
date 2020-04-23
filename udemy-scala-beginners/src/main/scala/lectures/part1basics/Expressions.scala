package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2 // EXPRESSION
  println(x)
  println(2 + 3 * 4)
  println(1 == x)
  println(!(1 == x))

  var aVariable = 2
  aVariable +=3
  println(aVariable)
  // Instructions (DO) vs Expressions (VALUE)
  // IF in SCALA is an expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 //IF EXPRESSION
  println(aConditionedValue)
  println(if(aCondition) 5 else 3)
  println(1 + 3)

  //NEVER WRITE THIS AGAIN
  //DON'T WRITE IMPERATIVE CODE ON SCALA
  //SCALA FORCES EVERYTHING IS AN EXPRESSION!!!
  var i=0
  while (i <10) {
    println(i)
    i += 1
  }

  // side effects: println(), whiles, reassigning
  val aWeirdValue = (aVariable = 3) // Unit === void
  println(aWeirdValue)

  // code blocks are expressions too
  // code block type = type of the last expression
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if(z > 2) "hello" else "goodbye"
  }

  //Exercises:
  //1. difference between "hello world" vs println("hello world")?
  //Ans: first one is a String type, second one is a Unit (side effect)
  //2.
  val someValue = {
    2 < 3
  }
  val someOtherValue = {
    if(someValue) 239 else 986
    42
  }
  println(someOtherValue)
  //Ans: this will return 42

  //Conclusion:
  // * Instructions are executed (Java), expression are evaluated (Scala)
  // * In Scala we think in terms of EXPRESSIONS
  // * Don't write loops in scala

}
