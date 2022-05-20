package memoryleak

import akka.actor.ActorSystem
import akka.stream.alpakka.s3.scaladsl.S3
import akka.stream.scaladsl.Sink
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.{ExecutionContext, Future}

object Leak extends LazyLogging {
  def checkS3ObjectExists(implicit config: Config, executionContext: ExecutionContext, system: ActorSystem): Future[Boolean] =
    for {
      result <- S3.download(config.bucket, config.key)
                  .runWith(Sink.headOption)
    } yield result.exists(_.isDefined)
}
