// AはAdder型クラスのインスタンスとなる型
trait Adder[A] {
  def zero: A
  def plus(x: A, y: A): A
}

def sum[A](list: List[A])(implicit adder: Adder[A]): A = {
  list.foldLeft(adder.zero)((x, y) => adder.plus(x, y))
}

implicit object IntAdder extends Adder[Int] {
  def zero: Int = 0
  def plus(x: Int, y: Int): Int = x + y
}

implicit object StringAdder extends Adder[String] {
  def zero: String = ""
  def plus(x: String, y: String): String = x + y
}