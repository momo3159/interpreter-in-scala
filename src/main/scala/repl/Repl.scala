package repl

import lexer.Lexer
import token.{EOF, Token}
object Repl extends App{
  private[this] val PROMPT = ">> "
  start()

  def start() = {
    while(true) {
      print(PROMPT)
      val in = io.StdIn.readLine()

      in match {
        case null =>
        case _ => {
          val l = new Lexer(in)
          val tokens = this.tokenize(l)
          for (tok <- tokens) {
            print(tok)
          }
          println("")
        }
      }

    }
  }

  private[this] def tokenize(l: Lexer): Seq[Token] = {
    val tok = l.nextToken()

    if (tok.kind == EOF) {
      Seq(tok)
    } else {
      tok +: tokenize(l)
    }
  }
}
