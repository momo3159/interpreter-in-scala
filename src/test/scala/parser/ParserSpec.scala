package parser

import ast.{LetStatement, Statement}
import lexer.Lexer
import org.scalatest.funsuite.AnyFunSuite

class ParserSpec extends AnyFunSuite {
  test("let 文の構文解析") {
    class TestCase(val expectedIdentifier: String) {}
    val input =
      """let x = 5;
         let y = 10;
         let foobar = 838383;
      """
    val tests = Seq(
      new TestCase("x"),
      new TestCase("y"),
      new TestCase("foobar")
    )

    val l = new Lexer(input)
    val p = new Parser(l)

    val program = p.parseProgram()
    if (program == null) {
      fail("parseProgram() return null")
    }

    assert(program.statements.length == 3)
    for ((elem, i) <- tests.zipWithIndex) {
      val stmt = program.statements(i)
      testLetStatement(stmt, elem.expectedIdentifier)
    }

  }

  def testLetStatement(s: Statement, name: String): Unit = {
    assert(s.tokenLiteral() == "let")
    val letStmt = s.asInstanceOf[LetStatement]
    assert(letStmt.name.value == name)
    assert(letStmt.name.tokenLiteral() == name)
  }
}
