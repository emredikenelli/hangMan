import java.util.NoSuchElementException

class MoveMaker (game: Game){
  val buyALetterCard = new Card("BUY_A_LETTER", 20, 1, false, true,
    false,0, 0, false)
  val discountCard = new Card("DISCOUNT", 5, 2, false, false,
    true, 0.75,  0, false)
  val riskCard = new Card("RISK", 8, 2, false, false,
    true, 0, 1, true)
  val consolationCard = new Card("CONSOLATION", 5, 1, false, false,
    true, 0, 0.5, false)
  val categoryCard = new Card("CATEGORY", 5, 1, true, false,
    false, 0, 0, false)
  val noCard = new Card("NO_CARD", 0, 30, false, false,
    true, 0, 0, false)

  val cardNames = Map(
    "BUY_A_LETTER" -> buyALetterCard, "DISCOUNT" -> discountCard, "RISK" -> riskCard,
    "CONSOLATION" -> consolationCard, "CATEGORY" -> categoryCard, "NO_CARD" -> noCard)



  def makeMove(card: Option[String], letter: Option[Char], index: Option[Int]): Unit = {
    try{
      if (card.isDefined){
        val mappedCard = cardNames(card.get)
        val nextMove = new Move(letter, Some(mappedCard), index)
        if (playible(Some(mappedCard), letter, index))
          game.playMove(nextMove)
      }
      else{
        val nextMove = new Move(letter, None, index)
        if (playible(None, letter, index)){
          game.playMove(nextMove)
        }
      }
    }
    catch{
      case ex: NoSuchElementException => println("no such card")
      case ex: IllegalArgumentException => println("cant make that move")
    }



    def playible(card: Option[Card], letter: Option[Char], index: Option[Int]): Boolean = {
      val letterCheck = if (letter.isDefined) {game.checkLetter(letter.get)} else true
      val cardCheck = if (card.isDefined) {game.checkCard(card.get)} else true
      val indexCheck = if (index.isDefined) {game.checkIndex(index.get)} else true

      letterCheck && cardCheck && indexCheck
    }


  }



}
