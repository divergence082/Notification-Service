package space.divergence.notification.template


trait Renderer {
  def render(template: String): String
}
