package space.divergence.notification.service

import space.divergence.notification.Subscriber

import scala.concurrent.Future

trait SubscriberService {
  def select(query: String, limit: Option[Int] = None): Future[List[Subscriber]]
}
