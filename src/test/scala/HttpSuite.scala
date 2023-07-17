import cats.effect.{IO, Resource}
import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.client.Client
import weaver.IOSuite

object HttpSuite extends IOSuite {
  override type Res = Client[IO]

  override def sharedResource: Resource[IO, Res] = BlazeClientBuilder[IO].resource

  test("Good requests lead to good results") {
    httpClient =>
      httpClient.get("https://httpbin.org/get") {
        response => IO.pure(response.status.code)
      }.map(x => {
        println("pass for here")
        expect(x == 200)
      })
  }

  test("test with for yield") {
    httpClient =>
      for {
        statusCode <- httpClient.get("https://httpbin.org/get") {
          response => IO.pure(response.status.code)
        }
      } yield expect(statusCode == 200)
  }

  test("Bad requests lead to bad results") { httpClient =>
    for {
      statusCode <- httpClient.get("https://httpbin.org/oops") {
        response => IO.pure(response.status.code)
      }
    } yield expect(statusCode == 404)
  }

}
