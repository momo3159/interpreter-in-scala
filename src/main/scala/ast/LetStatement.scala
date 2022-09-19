package ast

import token.Token

class LetStatement(val token: Token) extends Statement {
  var name: Identifier = null
  var value: Expression = null

  override def tokenLiteral(): String = this.token.literal
  override def statementNode(): Unit = ???
}
