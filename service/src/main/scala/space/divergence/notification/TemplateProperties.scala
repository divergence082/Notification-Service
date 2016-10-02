package space.divergence.notification


trait TemplateProperties {
  def withSubscriber(subscriber: Subscriber): TemplateProperties
}
