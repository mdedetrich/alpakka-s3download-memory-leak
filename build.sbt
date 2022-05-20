ThisBuild / name := "alpakka-s3download-memory-leak"

val akkaVersion           = "2.6.19"
val akkaHttpVersion       = "10.2.9"
val alpakkaVersion        = "3.0.4"
val pureConfigVersion     = "0.17.1"
val logbackClassicVersion = "1.2.11"
val scalaLoggingVersion   = "3.9.4"

ThisBuild / libraryDependencies ++= Seq(
  "com.typesafe.akka"          %% "akka-actor"             % akkaVersion,
  "com.typesafe.akka"          %% "akka-stream"            % akkaVersion,
  "com.typesafe.akka"          %% "akka-slf4j"             % akkaVersion,
  "com.lightbend.akka"         %% "akka-stream-alpakka-s3" % alpakkaVersion,
  "com.typesafe.akka"          %% "akka-http-xml"          % akkaHttpVersion,
  "com.typesafe.akka"          %% "akka-http"              % akkaHttpVersion,
  "com.github.pureconfig"      %% "pureconfig"             % pureConfigVersion,
  "ch.qos.logback"              % "logback-classic"        % logbackClassicVersion,
  "com.typesafe.scala-logging" %% "scala-logging"          % scalaLoggingVersion
)
