package lexer

import org.scalatest.funsuite.AnyFunSuite
import token.{ASSIGN, COMMA, EOF, FUNCTION, IDENT, INT, LBRACE, LET, LPAREN, PLUS, RBRACE, RPAREN, SEMICOLON, TokenKind}

class LexerSpec extends AnyFunSuite {
  case class TestCase (val expectedKind: TokenKind, val expectedLiteral: String)
  val input =
    """let five = 5;
      let ten = 10;

      let add = fn(x, y) {
        x + y;
      };

      let result = add(five, ten);
      """

  val tests: Seq[TestCase] = Seq(
    TestCase(LET, "let"),
    TestCase(IDENT, "five"),
    TestCase(ASSIGN, "="),
    TestCase(INT, "5"),
    TestCase(SEMICOLON, ";"),

    TestCase(LET, "let"),
    TestCase(IDENT, "ten"),
    TestCase(ASSIGN, "="),
    TestCase(INT, "10"),
    TestCase(SEMICOLON, ";"),

    TestCase(LET, "let"),
    TestCase(IDENT, "add"),
    TestCase(ASSIGN, "="),
    TestCase(FUNCTION, "fn"),
    TestCase(LPAREN, "("),
    TestCase(IDENT, "x"),
    TestCase(COMMA, ","),
    TestCase(IDENT, "y"),
    TestCase(RPAREN, ")"),
    TestCase(LBRACE, "{"),
    TestCase(IDENT, "x"),
    TestCase(PLUS, "+"),
    TestCase(IDENT, "y"),
    TestCase(SEMICOLON, ";"),
    TestCase(RBRACE, "}"),
    TestCase(SEMICOLON, ";"),

    TestCase(LET, "let"),
    TestCase(IDENT, "result"),
    TestCase(ASSIGN, "="),
    TestCase(IDENT, "add"),
    TestCase(LPAREN, "("),
    TestCase(IDENT, "five"),
    TestCase(COMMA, ","),
    TestCase(IDENT, "ten"),
    TestCase(RPAREN, ")"),
    TestCase(SEMICOLON, ";"),

    TestCase(EOF, "")
  )

  val l = new Lexer(input)
  test("トークン列が出力される") {
    for (tt <- tests) {
      val tok = l.nextToken()
      assert(tok.kind == tt.expectedKind)
      assert(tok.literal == tt.expectedLiteral)
    }
  }
}
