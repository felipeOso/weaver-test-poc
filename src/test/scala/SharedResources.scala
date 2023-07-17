import cats.effect.{IO, Resource}
import weaver.{GlobalResource, GlobalWrite}

object SharedResources extends GlobalResource {
  override def sharedResources(global: GlobalWrite): Resource[IO, Unit] =
    for {
      foo <- Resource.pure[IO, String]("Hello world")
      number <- Resource.pure[IO, Int](42)
      _ <- global.putR(foo)
      _ <- global.putR(number)
    } yield ()
}


