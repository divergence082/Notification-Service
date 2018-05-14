package space.divergence.notification


trait Template[Data, Message] {
  def render(data: Data): Message
}
