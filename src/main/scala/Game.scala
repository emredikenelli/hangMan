class Game (difficulty: String){
  val word = new Word
  var moveList: List[Move] = Nil
  var points = 100
  val buyALetterCard = new Card("BUY_A_LETTER", 20, 1)
  val discountCard = new Card("DISCOUNT", 5, 2)
  val riskCard = new Card("RISK", 8, 2)
  val consolationCard = new Card("CONSOLATION", 5, 1)
  val categoryCard = new Card("CATEGORY", 5, 1)



  val letterCosts = Map(
    'e' -> 20, 'a' -> 18, 'r' -> 16, 'i' -> 16, 'o' -> 15,
  't' -> 15, 'n' -> 15, 's' -> 14, 'l' -> 13, 'c' -> 12,
  'u' -> 11, 'd' -> 10, 'p' -> 10, 'm' -> 10, 'h' -> 10,
    'g' -> 9, 'b' -> 8, 'f' -> 8, 'y' -> 8, 'w' -> 6,
  'k' -> 6, 'v' -> 6, 'x' -> 5, 'z' -> 5, 'j' -> 5, 'q' -> 5
  )


  def checkLetter(letter: Char): Boolean = {
    if ('a' <= letter && letter <= 'z'){
      println("o harf bizde yok")
      return false
    }

    for (i <-  0  to moveList.length){
      if (letter == moveList(i) ){
        println("harfi kullanmisin kanka ya")
        return false
      }
    }
    return true
  }




  def requestMove(): Unit = {
    if (checkLetter(letter)){
      moveList = new Move(letter, card) :: moveList
    }
  }

  def guess(discount: Double): Unit = {
    if(word.exist(moveList.head.getLetter())){
      moveList.head.setResult("success")
    }
    else{
      val pointDecrease: Int = ( letterCosts(moveList.head.getLetter()) * (1 -discount)).toInt
      points = points - pointDecrease
      moveList.head.setResult("faliure")
    }
  }


  def playRiskCard(letter: Char): Unit = {
    riskCard.play()
    points = points - riskCard.getCost()
  }



}
