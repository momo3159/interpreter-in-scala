trait Namable {
  val name: String
  def display(): Unit = println(name)
}
