package space.divergence.notification.sms

import com.twitter.finagle.{Http, Service, http}
import space.divergence.notification.{Transport => NotificationTransport}

import scala.concurrent.Future


class Transport(url: String, apiKey: String) extends NotificationTransport[Address, Message] {

  import space.divergence.notification.sms.TwitterConversions.RichTwitterFuture

  private val client: Service[http.Request, http.Response] =
    Http.newService(url)

  override def send(address: Address, message: Message): Future[Unit] = {
    client(http.Request(http.Method.Get, s"$url?send=$message&to=$address&apikey=$apiKey"))
      .map(_ => ())
      .asScala
  }
}
