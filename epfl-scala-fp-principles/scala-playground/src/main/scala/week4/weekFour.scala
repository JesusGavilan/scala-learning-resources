package week4

import java.sql.Date

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

  // Enums

  enum DayOfWeek:
    case Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday

  import DayOfWeek.*

  def isWeekend(day: DayOfWeek) =  day match
    case Saturday | Sunday => true
    case _ => false

  enum Direction(val dx: Int, val dy: Int):
    case Right extends Direction(1, 0)
    case Up extends Direction(0,1)
    case Left extends  Direction(-1, 0)
    case Down extends Direction(0, -1)

    def leftTurn = Direction.values((ordinal +1) %4)

  val r = Direction.Right
  val u = r.leftTurn
  val v = (u.dx, u.dy)
  // ENUMs a shorthand for hierarchies of case classes ADTs
  // ADTs and enums are useful for domain modelling tasks.
  // where one needs to define a large number of data types
  // without attaching operations:
  // modelling payment methods
  enum PaymentMethod:
    case CreditCard(kind: Card, holder: String, number: Long, expires: Date)
    case PayPal(email: String)
    case Cash

  enum Card:
    case Visa, Mastercard, Amex

  //Variance
  trait Fruit
  class Apple extends Fruit
  class Orange extends Fruit

  type FtoO = Fruit => Orange
  type AtoF = Apple => Fruit


}
