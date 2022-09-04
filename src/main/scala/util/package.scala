package object util {
  def isLetter(ch: Char): Boolean = {
    'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z' || ch == '_'
  }

  def isDigit(ch: Char): Boolean = {
    '0' <= ch && ch <= '9'
  }
}
