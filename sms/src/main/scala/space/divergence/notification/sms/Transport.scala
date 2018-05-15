package space.divergence.notification.sms

import java.net.URLEncoder

import com.twitter.finagle.{Http, Service, http}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import space.divergence.notification.{Transport => NotificationTransport}

class Transport(url: String, apiKey: String) extends NotificationTransport[Address, Text] {

  import space.divergence.notification.TwitterConversions.RichTwitterFuture

  private val client: Service[http.Request, http.Response] = Http.client.newService(url)

  override def send(address: Address, message: Text): Future[Unit] = {
    val request = http.Request(
      http.Method.Get,
      s"/api.php?" +
        s"send=${URLEncoder.encode(message, "UTF-8")}&" +
        s"to=${URLEncoder.encode(address, "UTF-8")}&" +
        s"apikey=$apiKey"
    )
    request.host(address.split(":").head)

    client(request)
      .map(_ => ())
      .asScala
  }
}
