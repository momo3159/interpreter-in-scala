package lexer

import org.scalatest.funsuite.AnyFunSuite
import token.{ASSIGN, ASTERISK, BANG, COMMA, ELSE, EOF, EQ, FALSE, FUNCTION, GT, IDENT, IF, INT, LBRACE, LET, LPAREN, LT, MINUS, NOT_EQ, PLUS, RBRACE, RETURN, RPAREN, SEMICOLON, SLASH, TRUE, TokenKind}

class LexerSpec extends AnyFunSuite {
  case class TestCase (val expectedKind: TokenKind, val expectedLiteral: String)
  val input =
    """let five = 5;
      let ten = 10;

      let add = fn(x, y) {
        x + y;
      };

      let result = add(five, ten);
      !-/*5;
      5 < 10 > 5;

      if (5 < 10) {
        return true;
      } else {
        return false;
      }

      10 == 10;
      10 != 9;
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

    TestCase(BANG, "!"),
    TestCase(MINUS, "-"),
    TestCase(SLASH, "/"),
    TestCase(ASTERISK, "*"),
    TestCase(INT, "5"),
    TestCase(SEMICOLON, ";"),

    TestCase(INT, "5"),
    TestCase(LT, "<"),
    TestCase(INT, "10"),
    TestCase(GT, ">"),
    TestCase(INT, "5"),
    TestCase(SEMICOLON, ";"),

    TestCase(IF, "if"),
    TestCase(LPAREN, "("),
    TestCase(INT, "5"),
    TestCase(LT, "<"),
    TestCase(INT, "10"),
    TestCase(RPAREN, ")"),
    TestCase(LBRACE, "{"),
    TestCase(RETURN, "return"),
    TestCase(TRUE, "true"),
    TestCase(SEMICOLON, ";"),
    TestCase(RBRACE, "}"),
    TestCase(ELSE, "else"),
    TestCase(LBRACE, "{"),
    TestCase(RETURN, "return"),
    TestCase(FALSE, "false"),
    TestCase(SEMICOLON, ";"),
    TestCase(RBRACE, "}"),

    TestCase(INT, "10"),
    TestCase(EQ, "=="),
    TestCase(INT, "10"),
    TestCase(SEMICOLON, ";"),

    TestCase(INT, "10"),
    TestCase(NOT_EQ, "!="),
    TestCase(INT, "9"),
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
