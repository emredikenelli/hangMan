class Move (letter: Char , card: Card, i: Int){

  def this(letter: Char) = this(letter, new Card("NO_CARD", 0, 30, false, false,
    true, 0, 0, false), -1)

  def this(card: Card) = this('*', card, -1)

  def this(card: Card, index: Int) = this('*', card, index)

  def this(letter: Char, card: Card) = this(letter, card, -1)

  private var succeeded: Boolean = _

  val usedLetter = letter

  val usedCard = card

  val index = i

  def setResult(res: Boolean) = succeeded = res

  def isValid(): Boolean = {
    if (card.makesGuess && letter == '*')
      return false
    else if(card.opensLetter && i < 0)
      return false
    else if (!card.makesGuess && letter != '*')
      return false
    else
      return true
  }


  def calculateDiscountForNextMove(): Double = {
    if (card.nextGuessCondition == succeeded)
      return card.nextGuessDiscount
    else
      return 0
  }


}