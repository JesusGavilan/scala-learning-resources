package lectures.part8json
import scala.io.Source.fromResource
//8.1 manipulating a JSON tree structure
object json extends App {
  val output = ujson.Arr(
    ujson.Obj("hello" -> "world", "answer" -> 42),
    true
  )
  output(0)("hello") = "goodbye"
  output(0)("tags") = ujson.Arr("awesome", "yay", "wonderful")
  println(output)
  //8.2 Manipulating json string
  val jsonString = fromResource("ammonite-releases.json").mkString
  val data = ujson.read(jsonString)
  println(data(0))
  println(data(0)("url"))
  println(data(0)("author")("id"))
  //extracting typed values
  println(data(0)("url").str)
  println(data(0)("author")("id").num)
  //traversing JSON
  def traverse(v: ujson.Value): Iterable[String] = v match {
    case a: ujson.Arr => a.arr.map(traverse).flatten
    case o: ujson.Obj => o.obj.values.map(traverse).flatten
    case s: ujson.Str => Seq(s.str)
    case _ => Nil
  }
  println(traverse(data))

}
