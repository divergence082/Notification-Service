package space.divergence.notification.sms

import java.time.Instant
import java.util.UUID

import space.divergence.notification.{NotificationService, TemplateRegistry}
import space.divergence.notification.sms.{Transport => SmsTransport}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Sms extends App {

  type Key = String
  type Data = Map[Key, Text]

  private val registry: TemplateRegistry[Text] = TemplateRegistry.base

  private val srv = new NotificationService[Address, Text](
    registry,
    new SmsTransport(
      "smspilot.ru:80",
      sys.env("smspilot_apikey")
    )
  )

  private val f = registry
    .register(UUID.randomUUID().toString, "Hi $name! This is test at $time")
    .flatMap(templateId => srv
      .emit[Data](
      sys.env("phone"),
      templateId,
      Map(
        "name" -> "Valeria",
        "time" -> Instant.now().toString
      )))

  Await.ready(f, Duration.Inf)
}
