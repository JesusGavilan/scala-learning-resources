package lectures.part5ts

object Variance extends App {

  trait Animal
  class Dog       extends Animal
  class Cat       extends Animal
  class Crocodile extends Animal

  // what is variance?
  // "inheritance" -> type substitution of generics
// can Cage of animal be used for a Cat?
  class Cage[T]
  // yes - covariance
  class CCage[+T]
  val ccage: CCage[Animal] = new CCage[Cat]

  // no -invariance
  class ICage[T]
  // val icage: ICage[Animal]= new ICage[Cat]
  // val x: Int = "hello"

  // hell no - opposite = contravariance
  class XCage[-T]
  val xcage: XCage[Cat] = new XCage[Animal]

  class InvariantCage[T](animal: T) // invariant

  /*class ContravariantCage[-T](val animal: T)
  val catCage: XCage[Cat] = new XCage[Animal](new Crocodile)
   */

  /*class CovariantVariableCage[+T](var animal: T) // types of vars are in CONTRAVARIANT POSITION
  val ccage: CCage[Animal] = new CCage[Cat](new Cat)
  ccage.animal = new Crocodile
   */
  /*
  class ContravariantVariableCage[-T](var animal: T) // also in COVARIANT POSITION
  val catCage: XCage[Cat] = new XCage[Animal](new Crocodile)
   */
  class InvariantVariableCage[T](var animal: T) //ok

  class AnotherContravariantCage[-T] {
    def addAnimal(animal: T) = true
  }
  val acc: AnotherContravariantCage[Cat] = new AnotherContravariantCage[Animal]
  acc.addAnimal(new Cat)
  class Kitty extends Cat
  acc.addAnimal(new Kitty)

  class MyList[+A] {
    def add[B >: A](element: B): MyList[B] = new MyList[B] // widening the type
  }

  val emptyList       = new MyList[Kitty]
  val animals         = emptyList.add(new Kitty)
  val moreAnimals     = animals.add(new Cat)
  val evenMoreAnimals = moreAnimals.add(new Dog)

  // METHOD ARGUMENTS ARE IN CONTRAVARIANT POSITION

  // return Types
  class PetShop[-T] {
    /*def get(isItaPuppy: Boolean): T // METHOD RETURN TYPES ARE IN COVARIANT POSITION
    val catShop = new PetShop[Animal] {
      def get(isItaPuppy: Boolean): Animal = new Cat
    }

    val dogShop: PetShop[Dog] = catShop
    dogShop.get(true) // EVIL CAT!
     */
    def get[S <: T](isItaPuppy: Boolean, defaultAnimal: S): S = defaultAnimal
  }

  val shop: PetShop[Dog] = new PetShop[Animal]
  //val evilCat            = shop.get(true, new Cat)
  class TerraNova extends Dog
  val bigFurry = shop.get(true, new TerraNova)

  /*
    Big rule
      - method arguments are in CONTRAVARIANT position
      - return types are in COVARIANT position
 */

}
