package space.divergence.notification.service

import java.time.ZonedDateTime

import space.divergence.notification.Notification

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


trait SchedulerService {

  private def safeSchedule(message: String,
                           address: String,
                           time: ZonedDateTime): Future[Option[Notification]] =
    schedule(message, address, time).map(Some(_)).fallbackTo(Future.successful(None))

  def schedule(message: String,
               addresses: List[String],
               time: ZonedDateTime): Future[List[Notification]] =
    Future.sequence(addresses.map(address => safeSchedule(message, address, time))).map(_.flatten)

  def schedule(message: String, address: String, time: ZonedDateTime): Future[Notification]
  def unschedule(id: String): Future[List[Notification]]
  def scheduled(from: ZonedDateTime, to: ZonedDateTime): List[Notification]
}
