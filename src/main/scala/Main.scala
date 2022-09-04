import lexer.Lexer

object Main extends App {
  val input =
    """let five = 5;
      let ten = 10;

      let add = fn(x, y) {
        x + y;
      };

      let result = add(five, ten);
      """
  val l = new Lexer(input)
  l.nextToken()
  l.nextToken()
  l.nextToken()
}
