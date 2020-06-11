package lectures.part2oops

object InheritancesAndTraits extends App {
  // single class inheritance
  class Animal {
    val creatureType = "wild"
    //final def eat = println("nyamnyam")
    def eat = println("nyamnyam")
  }

  class Cat extends Animal {
    def crunch =  {
      eat
      println("crunch crunch")
    }
  }

  val cat  = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  //overriding
  /*class Dog extends Animal {
    override val creatureType: String = "domestic"
    override def eat: Unit = println("crunch, crunch")
  }*/
  class Dog (override val creatureType: String) extends Animal {

    override def eat: Unit = {
      super.eat
      println("crunch, crunch")
    }
  }

  val dog = new Dog("k9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("k9")
  unknownAnimal.eat

  // overriding vs overloading

  //super
  //preventing overrides
  // 1 - use final on member
  //2 . use final on entire class
  //3. sealed the class = extends classes in THIS FILES, prevent extension in other files


}
