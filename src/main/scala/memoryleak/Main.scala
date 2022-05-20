package memoryleak

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.duration._
import scala.concurrent._
import scala.io.StdIn
import scala.language.postfixOps

object Main extends App with AppConfig {
  implicit val executionContext: ExecutionContext = ExecutionContext.global
  implicit val system: ActorSystem                = ActorSystem()

  def waitForShutdownSignal(promise: Promise[Unit] = Promise[Unit]()): Future[Unit] = {
    sys.addShutdownHook {
      promise.trySuccess(())
    }
    Future {
      blocking {
        if (StdIn.readLine("Press RETURN to stop...\n") != null)
          promise.trySuccess(())
      }
    }
    promise.future
  }

  val p = Promise[Unit]()

  Source
    .repeat(())
    .throttle(1, 100 millis)
    .splitAfter(_ == ())
    .to(
      Sink.lazyFutureSink { () =>
        for {
          _ <- Leak.checkS3ObjectExists
        } yield Sink.ignore
      }
    )
    .run()

  Await.result(waitForShutdownSignal(p), Duration.Inf)

}
