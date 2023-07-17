import cats.effect.{IO, Resource}
import weaver.{GlobalRead, IOSuite}

class SharingSuite(global: GlobalRead) extends IOSuite {
  type Res = String
  def sharedResource: Resource[IO, String] = global.getOrFailR[String]()

  test("a stranger, from the outside ! ooooh") { sharedString =>
    IO(expect(sharedString == "Hello world"))
  }
}

class OtherSharingSuite(global: GlobalRead) extends IOSuite {
  type Res = Option[Int] //due to .getR for Int return a Option[Int]

  def sharedResource: Resource[IO, Option[Int]] =
    global.getR[Int]()

  test("oops, forgot something here") { sharedInt =>
    IO(expect(sharedInt == Some(42)))
  }
}

/**to run one test, sbt testOnly *My*Suite (yourproject.resource.MyResources= object that extends from GlobalResource)*/