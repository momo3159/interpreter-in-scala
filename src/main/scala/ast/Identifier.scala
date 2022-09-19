package ast

import token.Token

/*
* 式のなかで使われる場合は値を生成するが，変数宣言時には生成しない
* しかし，ここでは簡単のため全て Expression として扱う．
* */
class Identifier(val token: Token, val value: String) extends Expression {
  override def tokenLiteral(): String = this.token.literal
  override def expressionNode(): Unit = ???
}
