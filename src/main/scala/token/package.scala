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
  val MINUS: TokenKind = "-"
  val ASTERISK: TokenKind = "*"
  val SLASH: TokenKind = "/"
  val BANG: TokenKind = "!"
  val LT: TokenKind = "<"
  val GT: TokenKind = ">"
  val EQ: TokenKind = "=="
  val NOT_EQ: TokenKind = "!="

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
  val RETURN: TokenKind = "RETURN"
  val IF: TokenKind = "IF"
  val ELSE: TokenKind = "ELSE"
  val TRUE: TokenKind = "TRUE"
  val FALSE: TokenKind = "FALSE"

  val keywords = Map(
    "fn" -> FUNCTION,
    "let" -> LET,
    "return" -> RETURN,
    "if" -> IF,
    "else" -> ELSE,
    "true" -> TRUE,
    "false" -> FALSE
  )


  def lookupIdent(ident: String) = {
    keywords.getOrElse(ident, IDENT)
  }
}
