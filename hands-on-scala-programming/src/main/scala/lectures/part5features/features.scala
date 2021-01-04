package lectures.part5features

object features extends App {
  //5.1 Pattern matchinng to parse simple string pattern

  def getDayMonthYear(s: String) = s match {
    case s"$day-$month-$year" => println(s"found day: $day, month: $month, year: $year")
    case _ => println("not a date")
  }

  getDayMonthYear("9-8-1987")
  getDayMonthYear("9-8")

  //5.1.3 seal trait
  //in general, sealed traits are good for modelling hierarchies you expect the number of sub-classes to change
  // very little or not at all. Example JSOn which can only be null, boolean, number, string, array or dictionary
  //so, the number of subclasses are fixed, the range of operations are unbounded: parse, serialize it, pretty print,etc
  sealed trait Json
  case class Null() extends Json
  case class Bool(value: Boolean) extends Json
  case class Str(value: String) extends Json
  case class Num(value: Double) extends Json
  case class Arr(value: Seq[Json]) extends Json
  case class Dict(value: Map[String, Json]) extends Json

  //5.2 pattern matching

  // on case classes
  case class Point(x:Int, y: Int)

  def direction(p: Point) = p match {
    case Point(0,0) => "origin"
    case Point(_,0) => "horizontal"
    case Point(0,_) => "vertical"
    case _ => "diagonal"
  }
  println(direction(Point(0,0)))
  println(direction(Point(1,1)))
  println(direction(Point(10,0)))

}
