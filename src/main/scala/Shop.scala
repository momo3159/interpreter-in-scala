
class Shop (val name: String) extends AnyRef with Enumerable[A] with Namable {
  private[this] staffs: List[Staff] = List(new Staff("太郎", 16), new Staff("花子", 16))

  override def foreach[B](f: A => B): Unit = staffs.foreach(f)
}
