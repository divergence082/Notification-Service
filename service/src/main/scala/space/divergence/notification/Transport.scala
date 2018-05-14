package space.divergence.notification

import scala.concurrent.Future


trait Transport[Address, Message] {
  def send(address: Address, message: Message): Future[Unit]
}