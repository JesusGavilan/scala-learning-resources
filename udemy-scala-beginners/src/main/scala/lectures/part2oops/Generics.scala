package lectures.part2oops

object Generics extends App {

  class MyList[+A] {
    // use the type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
     A = Cat
     B = Animal
     */
  }
  class MyMap[Key, Value]
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. Yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // animalList.add(new Dow)??? HARD QUESTION => We return a list of Animals

  //2. No = INVARIANCE
  class InvariantList[A]
  //val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] //Fail
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  //3. NO, CONTRAVARIANCE
  class Trainer[-A]
  val contravarintList: Trainer[Cat] = new Trainer[Animal]

  // bounded types

  class Cage[A <: Animal](animal: A)  //class Cage only accepts type parameter A which are subtypes of Animal
  val cage = new Cage(new Dog)
  class Car
  //val newCage = new Cage(new Car) //Will fail

  // expand MyList to be generic





}
