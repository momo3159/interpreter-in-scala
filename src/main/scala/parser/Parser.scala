package parser

import ast.{Identifier, LetStatement, Program, ReturnStatement}
import lexer.Lexer
import token.{ASSIGN, EOF, IDENT, LET, RETURN, SEMICOLON, Token}

class Parser(val l: Lexer) {
  var curToken: Token = null
  var peekToken: Token = null
  var errors: Seq[String] = Seq.empty
  this.nextToken()
  this.nextToken()

  def nextToken() = {
    this.curToken = this.peekToken
    this.peekToken = this.l.nextToken()
  }

  def parseProgram(): Program = {
    var program = new Program

    while(this.curToken.kind != EOF) {
      val stmt = this.parseStatement
      if (stmt != null) {
        program.statements = program.statements :+ stmt
      }
      this.nextToken()
    }

    program
  }

  def parseStatement() = {
    this.curToken.kind match {
      case LET => this.parseLetStatement()
      case RETURN => this.parseReturnStatement()
      case _ => null
    }
  }

  def parseLetStatement(): LetStatement = {
    val stmt = new LetStatement(this.curToken)

    if (!this.expectPeek(IDENT)) {
      return null
    }

    stmt.name = new Identifier(this.curToken, this.curToken.literal)

    if (!this.expectPeek(ASSIGN)) {
      return null
    }

    // TODO: expression の構文解析は一旦飛ばす
    while (!this.curTokenIs(SEMICOLON)) {
      this.nextToken()
    }

    stmt
  }

  def parseReturnStatement(): ReturnStatement = {
    val stmt = new ReturnStatement(this.curToken)

    // TODO: 式の構文解析はとばす
    while(!this.curTokenIs(SEMICOLON)) {
      this.nextToken()
    }

    stmt
  }

  def curTokenIs(kind: token.TokenKind) = {
    this.curToken.kind == kind
  }

  def peekTokenIs(kind: token.TokenKind) = {
    this.peekToken.kind == kind
  }

  def expectPeek(kind: token.TokenKind) = {
    if (this.peekTokenIs(kind)) {
      this.nextToken()
      true
    } else {
      this.peekError(kind)
      false
    }
  }

  def getErrors() = this.errors
  def peekError(kind: token.TokenKind) = {
    // 次に来てほしいトークンと実際のトークンが異なる時に出すエラーメッセージ
    val msg = s"expected next token to be ${kind}, got ${this.peekToken.kind} instead"
    this.errors = this.errors :+ msg
  }
}
