package token

type TokenKind = String
class Token (val kind: TokenKind, val literal: String)
