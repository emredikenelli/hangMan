class Card (cardType: String, cost: Int, var number: Int){

  def getCardType(): String = cardType
  def play(): Unit = {number = number - 1}
  def getCost(): Int = cost

}
