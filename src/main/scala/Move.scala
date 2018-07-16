class Move (letter: Option[Char] , card: Option[Card], i: Option[Int]){

  require(isValid())

  private var succeeded: Boolean = _

  val usedLetter = letter

  val usedCard = card

  val index = i

  def setResult(res: Boolean) = succeeded = res

  def isValid(): Boolean = {
    if(card.isDefined){
      if (card.get.makesGuess && !letter.isDefined)
        return false
      else if(card.get.opensLetter && !i.isDefined)
        return false
      else if (!card.get.makesGuess && letter.isDefined)
        return false
      else true
    }
    else if (i.isDefined)
      return false
    else if (!letter.isDefined)
      return false
    else
      return true

  }


  def calculateDiscountForNextMove(): Double = {
    if (!card.isDefined)
      return 0
    else if (card.get.nextGuessCondition == succeeded)
      return card.get.nextGuessDiscount
    else
      return 0
  }


}