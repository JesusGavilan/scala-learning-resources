package lectures.part2oops

//import playground._
import playground.{PrinceCharming, Cinderella => Princess}
import java.util.Date
import java.sql.{SQLData, Date => SqlDate}

object PackagingAndImports {
  // package members area accessible by their simple name
  val writer = new Writer("Jesus", "Example", 2020)

  // import the package
  val princess = new Princess
  //val princess = new playground.Cinderella // fully qualified name

  // packages are in hierarchy
  // matching folder structure

  //package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // Same name imports
  //1. ue FQ names or use aliasing
  val date = new Date
  val sqlDate = new SqlDate(2018,5,4)

  // default imports
  // java.lang - String, Object, Exceptioned

  // Scala - Int, Nothing, Function
  // scala.Predef - println, ???


}
