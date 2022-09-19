package ast

import token.Token

class ReturnStatement(val token: Token) extends Statement {
  var returnValue = null
  override def tokenLiteral(): String = this.token.literal
  override def statementNode(): Unit = ???
}
