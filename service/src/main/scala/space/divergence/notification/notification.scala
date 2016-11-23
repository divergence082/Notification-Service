package space.divergence.notification


trait Message

trait Address

trait Data

trait Template {
  def render(data: Data): Message
}
