class Card (cardType: String, cost: Int, var count: Int, revealsCategory: Boolean, opensLetter: Boolean,
            makesGuess: Boolean, guessDiscount: Double,
            nextGuessDiscount: Double, nextGuessCondition: Boolean){

  def play(): Unit = {count = count - 1}
  def getCardType(): String = cardType
  def getCount(): Int = count
  def getCost(): Int = cost
  def getRevealsCategory(): Boolean = revealsCategory
  def getOpensLetter(): Boolean = opensLetter
  def getMakesGuess(): Boolean = makesGuess
  def getGuessDiscount(): Double = guessDiscount
  def getNextGuessDiscount(): Double = nextGuessDiscount
  def getNextGuessCondition(): Boolean = nextGuessCondition

}
