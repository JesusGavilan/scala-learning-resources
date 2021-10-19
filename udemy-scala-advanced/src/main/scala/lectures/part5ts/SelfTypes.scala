package lectures.part5ts

object SelfTypes extends App {
  // requiring a type to be mixed in
  trait Instrumentalist {
    def play(): Unit
  }

  trait Singer { self: Instrumentalist => // whoever implements Singer to implement Instrumentalist
    def sing(): Unit
  }

  class LeadSinger extends Singer with Instrumentalist {
    override def play(): Unit = ???
    override def sing(): Unit = ???
  }

  //class Vocalist extends Singer {
  //  override def sing(): Unit = ???
  //}

  val jamesHetfield = new Singer with Instrumentalist {
    override def play(): Unit = ???
    override def sing(): Unit = ???
  }

  class Guitarist extends Instrumentalist {
    override def play(): Unit = ???
  }

  val ericClapton = new Guitarist with Singer {
    override def sing(): Unit = ???
  }

  // Whats is the difference with inheritance????
  class A
  class B extends A // means B is an A

  trait T
  trait S { self: T =>
  } // means S requires a T
  // CAKE PATTERN => "dependency injection"

  // CLassical Dependency Injection
  class Component {
    // API
  }
  class ComponentA extends Component
  class ComponentB extends Component
  class DependentComponent(val Ccomponent: Component)

  // CAKE PATTERN
  trait ScalaComponent {
    //API
    def action(x: Int): String
  }
  trait ScalaDependentComponent { self: ScalaComponent =>
    def dependentAction(x: Int): String = action(x) + " this rocks!"
  }
  trait ScalaApplication { self: ScalaDependentComponent =>
  }

  //layer 1 -small components
  trait Picture extends ScalaComponent
  trait Stats   extends ScalaComponent

  //layer 2 - compose
  trait Profile   extends ScalaDependentComponent with Picture
  trait Analytics extends ScalaDependentComponent with Stats

  //layer 3 - app
  trait AnalyticsApp extends ScalaApplication with Analytics

  // cyclical dependencies
  // class X extends Y
  // class Y extends X

  trait X { self: Y =>
  }
  trait Y { self: X =>
  }
}
