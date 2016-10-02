package space.divergence.notification.service

import space.divergence.notification.Notification

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ChannelService {
  private def sendSafe(message: String, address: String): Future[Option[Notification]] =
    send(message, address).map(Some(_)).fallbackTo(Future.successful(None))

  def send(message: String, addresses: List[String]): Future[List[Notification]] =
    Future.sequence(addresses.map(address => sendSafe(message, address))).map(_.flatten)

  def send(message: String, address: String): Future[Notification] = ???
}
