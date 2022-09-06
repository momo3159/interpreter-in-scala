package lexer

import token.{ASSIGN, ASTERISK, BANG, COMMA, EOF, EQ, GT, IDENT, ILLEGAL, INT, LBRACE, LPAREN, LT, MINUS, NOT_EQ, PLUS, RBRACE, RPAREN, SEMICOLON, SLASH, Token, lookupIdent}
import util.{isDigit, isLetter}

// TODO: ch を8bit 符号なし整数で扱う
class Lexer(val input: String) {
  var position: Int = 0
  var readPosition: Int = 0
  var ch: Char = 0
  this.readChar()

  def readChar() = {
    if (this.readPosition >= this.input.length) {
      this.ch = 0
    } else {
      this.ch = this.input(this.readPosition)
    }
    this.position = this.readPosition
    this.readPosition += 1
  }

  def nextToken(): Token = {
    this.skipWhiteSpace()

    val tok = this.ch match {
      case '='=> {
        if (this.peekChar == '=') {
          val ch = this.ch
          this.readChar()
          val tok = ch.toString + this.ch.toString
          new Token(EQ, tok)
        } else {
          new Token(ASSIGN, this.ch.toString)
        }
      }
      case '+' => new Token(PLUS, this.ch.toString)
      case '-' => new Token(MINUS, this.ch.toString)
      case '*' => new Token(ASTERISK, this.ch.toString)
      case '/' => new Token(SLASH, this.ch.toString)
      case '!' => {
        if (this.peekChar == '=') {
          val ch = this.ch
          this.readChar()
          val tok = ch.toString + this.ch.toString
          new Token(NOT_EQ, tok)
        } else {
          new Token(BANG, this.ch.toString)
        }
      }
      case '<' => new Token(LT, this.ch.toString)
      case '>' => new Token(GT, this.ch.toString)
      case ',' => new Token(COMMA, this.ch.toString)
      case ';' => new Token(SEMICOLON, this.ch.toString)
      case '(' => new Token(LPAREN, this.ch.toString)
      case ')' => new Token(RPAREN, this.ch.toString)
      case '{' => new Token(LBRACE, this.ch.toString)
      case '}' => new Token(RBRACE, this.ch.toString)
      case 0   => new Token(EOF, "")
      case _ => {
        if(isLetter(this.ch)) {
          val literal = this.readIdentifier()
          val kind = lookupIdent(literal)
          return new Token(kind, literal)
        } else if (isDigit(this.ch)) {
          val literal = this.readNumber()
          return new Token(INT, literal)
        } else {
          new Token(ILLEGAL, this.ch.toString)
        }
      }
    }

    this.readChar
    tok
  }

  def readIdentifier() = {
    val position = this.position

    while (isLetter(this.ch)) {
      this.readChar()
    }
    this.input.substring(position, this.position)
  }

  def skipWhiteSpace() = {
    while (this.ch == ' ' || this.ch == '\t' || this.ch == '\n' || this.ch == '\r') {
      this.readChar()
    }
  }

  def readNumber() = {
    val position = this.position
    while (isDigit(this.ch)) {
      this.readChar()
    }
    this.input.substring(position, this.position)
  }

  def peekChar() = {
    // 次に読み込む文字を覗き見する（indexは進めない）
    // どれだけ先を読む必要があるかによって，ソースコードの解析の難易度が変わる
    if (this.readPosition >= this.input.length) {
      0.asInstanceOf[Char]
    } else {
      this.input(this.readPosition)
    }
  }
}
