class Move (letter: Char = '*', card: Card = new Card("NO_CARD", 0, 30, false,
  false, true, 0, 0, false)){

  var succeeded: Boolean = _

  def isValid(): Boolean = {
    if (card.getCardType().equals("CATEGORY") || card.getCardType().equals("BUY_A_LETTER")){
      if(letter.equals('*'))
        return true
      else
        return false
    }
    else if(letter.equals('*'))
      return false
    else
      return true
  }

  def getLetter(): Char = {letter}

  def getCard(): Card = card

  def getResult: Boolean = succeeded

  def setResult(res: Boolean): Unit = {succeeded = res}

  def calculateDiscountForNextMove(): Double = {
    if (card.getNextGuessCondition() == succeeded)
      return card.getNextGuessDiscount()
    else
      return 0
  }


}
