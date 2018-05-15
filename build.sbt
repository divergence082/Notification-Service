

lazy val finagleVersion = "18.5.0"

lazy val projectSettings = Seq(
  organization := "space.divergence",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.6"
)

lazy val publishSettings = Seq(
  publishMavenStyle := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  pomExtra := (
    <url>https://github.com/divergence082/Notification-Service</url>
      <licenses>
        <license>
          <name>BSD-style</name>
          <url>http://www.opensource.org/licenses/bsd-license.php</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <scm>
        <url>git@github.com:divergence082/Notification-Service.git</url>
        <connection>scm:git:git@github.com:divergence082/Notification-Service.git</connection>
      </scm>
      <developers>
        <developer>
          <id>divergence082</id>
          <name>Valeria Kononenko</name>
          <email>divergence082@gmail.com</email>
          <url>https://github.com/divergence082</url>
        </developer>
      </developers>)
)

lazy val scalastyleSettings = Seq(
  scalastyleConfig in Compile := baseDirectory.value / "project" / "scalastyle-config.xml",
  scalastyleConfig in Test := baseDirectory.value / "project" / "scalastyle-config.xml"
)

lazy val root = (project in file("."))
  .aggregate(service, sms)

lazy val service = (project in file("service"))
  .settings(projectSettings, publishSettings, scalastyleSettings)

lazy val sms = (project in file("sms"))
  .settings(projectSettings, publishSettings, scalastyleSettings,
    libraryDependencies ++= Seq(
      "com.twitter" %% "finagle-http" % finagleVersion
    )
  )
  .dependsOn(service)