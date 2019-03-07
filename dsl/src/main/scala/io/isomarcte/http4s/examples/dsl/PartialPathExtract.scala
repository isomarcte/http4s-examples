package io.isomarcte.http4s.examples.dsl

import cats._
import cats.effect._
import fs2._
import org.http4s._
import org.http4s.dsl._
import org.http4s.server.blaze._
import scala.concurrent.ExecutionContext.Implicits._

/** Example Of Using `/:` Operator
  *
  * Run with: sbt dsl/run
  *
  * Example response using curl.
  * {{{
  * > curl 'localhost:8080/foo': 7 ~/git/personal/http4s-examples λ > curl 'localhost:8080/foo'
  * Bar is: List()
  *  > curl 'localhost:8080/foo/bar'
  * Bar is: List(bar)
  * }}}
  */
object PartialPathExtract extends StreamApp[IO] with Http4sDsl[IO] {

  val route: HttpService[IO] =
    HttpService{
      case GET -> "foo" /: bar =>
        Ok(s"Bar is: ${bar.toList}\n")
    }

  override final def stream(args: List[String], shutdown: IO[Unit]): Stream[IO, StreamApp.ExitCode] =
    BlazeBuilder[IO].bindHttp(8080, "localhost").mountService(this.route, "/").serve
}
