package token

case class Token (val kind: TokenKind, val literal: String) {
  override def toString: TokenKind = {
    s"""
    {
        Kind: ${this.kind}
        Literal:\"${this.literal}\"
    }"""
  }
}
