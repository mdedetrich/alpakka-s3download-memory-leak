package memoryleak

import pureconfig.ConfigSource

final case class Config(bucket: String, key: String)

trait AppConfig {
  import pureconfig.generic.auto._
  implicit lazy val config: Config = ConfigSource.default.at("config").loadOrThrow[Config]
}

object AppConfig extends AppConfig
