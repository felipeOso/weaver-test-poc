import cats.effect._
import weaver._

/** Starting with version 0.8.0, Weaver no longer offers out-of-the-box support for other effect types than cats-effect */
object MySuiteIO extends SimpleIOSuite {

  val random = IO(java.util.UUID.randomUUID())

  test("hi side-effects") {
    for {
      x <- random
      y <- random
    } yield expect(x != y)
  }

  test("use failfast method to evaluate the expectation eagerly and raise assertion error in your effect type") {
    for {
      x <- IO("hello")
      _ = println("test")
      y = x + "bla"
      _ <- expect(y.size > x.size).failFast
      _ = println("test 1")
      _ <- expect(x.length == 3).failFast
      _ = println("test 2")
    } yield expect(y.contains(x))
  }



}
