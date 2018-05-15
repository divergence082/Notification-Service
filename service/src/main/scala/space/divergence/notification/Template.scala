package space.divergence.notification


trait Template[Message] {
  def render[Data](data: Data): Message
}

object Template {

  type Msg = String

  private class Base(text: Msg) extends Template[Msg] {
    type Key = String

    override def render[Data](data: Data): Msg = {
      data match {
        case m: Map[_, _] => m.foldLeft(text)({
          case (txt: Msg, (key: Key, value: String)) => txt.replaceAllLiterally(s"$$$key", value)
        })

        case _ => ""
      }
    }
  }

  def base(text: Msg): Template[Msg] = new Base(text)

}