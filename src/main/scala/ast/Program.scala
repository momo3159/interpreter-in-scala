package ast

class Program extends Node {
  var statements = Seq.empty[Statement]

  def tokenLiteral() = {
    if (statements.length > 0) {
      this.statements(0).tokenLiteral()
    } else {
      ""
    }
  }
}
