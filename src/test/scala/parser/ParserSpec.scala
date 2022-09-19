package parser

import ast.{LetStatement, ReturnStatement, Statement}
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
    checkParseErrors(p)
    if (program == null) {
      fail("parseProgram() return null")
    }

    assert(program.statements.length == 3)
    for ((elem, i) <- tests.zipWithIndex) {
      val stmt = program.statements(i)
      testLetStatement(stmt, elem.expectedIdentifier)
    }

  }

  test("return 文の構文解析") {
    val input =
      """return 5;
         return 10;
         return 993322;
      """

    val l = new Lexer(input)
    val p = new Parser(l)
    val program = p.parseProgram()
    checkParseErrors(p)

    assert(program.statements.length == 3)
    for (stmt <- program.statements) {
      val returnStmt = stmt.asInstanceOf[ReturnStatement]
      assert(returnStmt.tokenLiteral() == "return")
    }
  }

  def testLetStatement(s: Statement, name: String): Unit = {
    assert(s.tokenLiteral() == "let")
    val letStmt = s.asInstanceOf[LetStatement]
    assert(letStmt.name.value == name)
    assert(letStmt.name.tokenLiteral() == name)
  }

  def checkParseErrors(p: Parser) = {
    val errors = p.getErrors()
    if (errors.length != 0) {
      var msg = s"parser has ${errors.length} errors.\n"
      for (e <- errors) {
        msg += s"parser error: ${e}\n"
      }

      fail(msg)
    }
  }
}
