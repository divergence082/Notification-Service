package space.divergence.notification

import scala.concurrent.Future


trait Transport {
  def send(address: Address, message: Message): Future[Unit]
}