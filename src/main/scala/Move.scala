class Move (letter: Char, card: Card){

  var result = "unknown"

  def getLetter(): Char = {letter}
  def getCard(): Card = card
  def setResult(res: String): Unit = {result = res}

}
