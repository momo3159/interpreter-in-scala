package object token {
  type TokenKind = String

  val ILLEGAL: TokenKind = "ILLEGAL"
  val EOF: TokenKind = "EOF"

  // 識別子，リテラル
  val IDENT: TokenKind = "IDENT"
  val INT: TokenKind = "INT"

  // 演算子
  val ASSIGN: TokenKind = "="
  val PLUS: TokenKind = "+"

  // デリミタ
  val COMMA: TokenKind = ","
  val SEMICOLON: TokenKind = ";"

  val LPAREN: TokenKind = "("
  val RPAREN: TokenKind = ")"
  val LBRACE: TokenKind = "{"
  val RBRACE: TokenKind = "}"

  // 予約語
  val FUNCTION: TokenKind = "FUNCTION"
  val LET: TokenKind = "LET"

  val keywords = Map("fn" -> FUNCTION, "let" -> LET)

  def lookupIdent(ident: String) = {
    keywords.getOrElse(ident, IDENT)
  }
}
