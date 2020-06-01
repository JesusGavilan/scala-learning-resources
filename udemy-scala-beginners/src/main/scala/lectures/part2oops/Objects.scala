package lectures.part2oops

object Objects extends App{
  // SCALA DOES NOT HAVE CLASS LEVEL FUNCTIONALITY ("static")
  // SCALA HAS OBJECTS
  // Declared like classes but objects have no parameters

  object Person { //Type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean  = false
    //factory method, could habe another name
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person (val name: String) {
    // instance-level functionality
  }
  // THIS PATTERN is called COMPANION (same name and scope)
  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON INSTANCE
  val mary = Person
  val john = Person
  println( mary == john) //same objects cause is a singleton (point to the same object)

  val anna = new Person("Anna")
  val tom = new Person("Tom")
  println( anna == tom)

  val bobbie = Person(anna, tom)

  //Scala applications = Scala object with
  // def main(args: Array[String]): Unit

  //Conclusions
  //1. Scala does not hve static values/methods
  //2. Scala objects are in their own class, the only instances, single pattern in one line
  //3. Scala companions can acces each other private members

}
