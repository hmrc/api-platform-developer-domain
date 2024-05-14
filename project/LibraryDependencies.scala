import sbt._

object LibraryDependencies {
  def apply() = compileDependencies ++ testDependencies

  val commonDomainVersion = "0.13.0"

  lazy val compileDependencies = Seq(
    "uk.gov.hmrc"             %% "api-platform-common-domain"     % commonDomainVersion
  )

  lazy val testDependencies = Seq(
    "org.scalactic"           %% "scalactic"                        % "3.2.14",
    "com.vladsch.flexmark"     % "flexmark-all"                     % "0.62.2",
    "org.mockito"             %% "mockito-scala-scalatest"          % "1.17.29",
    "org.scalatest"           %% "scalatest"                        % "3.2.17",
    "uk.gov.hmrc"             %% "api-platform-test-common-domain"  % commonDomainVersion
  ).map(_ % "test")
}
