package week4

object weekFour extends App {

  /*
  trait Expr:
    def isNumber: Boolean
    def isSum: Boolean
    def numValue: Int
    def leftOp: Expr
    def rightOp: Expr

  class Number(n: Int) extends Expr:
    def isNumber = true
    def isSum = false
    def numValue = n
    def leftOp = throw Error("Number.leftOp")
    def rightOp = throw Error("Number.rightOp")

  class Sum(e1: Expr, e2: Expr) extends Expr:
    def isNumber = false
    def isSum = true
    def numValue = throw Error("Sum.numValue")
    def leftOp = e1
    def rightOp = e2


  def eval(e: Expr): Int =
    if e.isNumber then e.numValue
    else if e.isSum then eval(e.leftOp) + eval(e.rightOp)
    else throw Error("Unknown expression " + e)
   */
  trait Expr

  case class Number(n: Int)          extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Var(name: String) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def eval(e: Expr): Int = e match
    case Number(n) => n
    case Sum(e1, e2) => eval(e1) + eval(e2)
  val expr = Sum(Number(1), Number(2))
  eval(expr)

  def show(e: Expr): String = e match
    case Number(n) => n.toString
    case Sum(e1,e2) => s"${show(e1)} + ${show(e2)}"
    case Var(x) => x
    case Prod(e1,e2) => s"${showP(e1)} * ${showP(e2)}"

  def showP(e: Expr): String = e match
    case e: Sum => s"(${show(e)})"
    case _ => show(e)

  println(show(expr))
  val expr1 = Prod(expr, Var("x"))
  println(show(expr1))

}
