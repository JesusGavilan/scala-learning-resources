package lectures.part2fp

object Monads extends App {
  // our own Try monad
  trait Attempt[+A] {
    def flatMap[B](f: A => Attempt[B]): Attempt[B]
  }

  object Attempt {
    def apply[A](a: => A): Attempt[A] =
      try {
        Success(a)
      } catch {
        case e: Throwable => Fail(e)
      }
  }

  case class Success[+A](value: A) extends Attempt[A] {
    def flatMap[B](f: A => Attempt[B]): Attempt[B] =
      try {
        f(value)
      } catch {
        case e: Throwable => Fail(e)
      }
  }

  case class Fail(e: Throwable) extends Attempt[Nothing] {
    def flatMap[B](f: Nothing => Attempt[B]): Attempt[B] = this
  }

  /*
    left-identity

    unit.flatMap(f) = f(x)
    Attempt(x).flatMap(f) = f(x) // Success case!
    Success(x).flatMap(f) = f(x) // proved.

    right-identity

    attempt.flatMap(unit) = attempt
    Success(x).flatMap(x => Attempt(x)) = Attempt(x) = Success(x)
    Fail(e).flatMap(...) = Fail(e)

    associativity

    attempt.flatMap(f).flatMap(g) == attempt.flatMap(x => f(x).flatMap(g))
    Fail(e).flatMap(f).flatMap(g) = Fail(e)
    Fail(e).flatMap(x => f(x).flatMap(g)) = False(e)

    Success(v).flatMap(f).flatMap(g) =
      f(v).flatMap(g) OR Fail(e)

    Success(v).flatMap(x => f(x).flatMap(g)) =
    f(v).flatMap(g) OR Fail(e)
   */
  val attempt = Attempt {
    throw new RuntimeException("My own monad, yes!")
  }
  println(attempt)

  /*
   1) Exercise: Implement a Lazy[T] monad = computation which will only be executed when it's needed
   unit/apply
   flatMap

   2) Monads = unit + flatMap
      Monads = unit + map + flatten

      Monad[T[ {
      def flatMap[b](f: T => Monad[B]: Monad[B] = ... (implemented)

      def map[B](f: T =
      }
   */
  // 1 - Lazy monad
  class Lazy[+A](value: => A) {
    // call by need
    private lazy val internalValue                = value
    def use: A                                    = internalValue
    def flatMap[B](f: (=> A) => Lazy[B]): Lazy[B] = f(internalValue)
  }
  object Lazy {
    def apply[A](value: => A): Lazy[A] = new Lazy(value) //unit
  }

  val lazyInstance = Lazy {
    println("Today I don't feel like doing anything")
    42
  }
  val flatMappedInstance = lazyInstance.flatMap(x =>
    Lazy {
      10 * x
  })
  val flatMappedInstance2 = lazyInstance.flatMap(x =>
    Lazy {
      10 * x
  })
  flatMappedInstance.use
  flatMappedInstance2.use

}
