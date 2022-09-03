package object token {
  type TokenKind = String

  val ILLEGAL: TokenKind = "ILLEGAL"
  val EOF: TokenKind = "EOF"

  // 識別子，リテラル
  val IDENT: TokenKind = "ILLEGAL"
  val INT: TokenKind = "ILLEGAL"

  // 演算子
  val ASSIGN: TokenKind = "ILLEGAL"
  val PLUS: TokenKind = "ILLEGAL"

  // デリミタ
  val COMMA: TokenKind = "ILLEGAL"
  val SEMICOLON: TokenKind = "ILLEGAL"

  val LPAREN: TokenKind = "ILLEGAL"
  val RPAREN: TokenKind = ")"
  val LBRACE: TokenKind = "{"
  val RBRACE: TokenKind = "}"

  // 予約語
  val FUNCTION: TokenKind = "FUNCTION"
  val LET: TokenKind = "LET"
}
