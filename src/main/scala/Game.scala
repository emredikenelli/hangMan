class Game (difficulty: String){
  val word = new Word
  var moveList: List[Move] = Nil
  var points = 100
  reportStatus()



  val letterCosts = Map(
    'e' -> 20, 'a' -> 18, 'r' -> 16, 'i' -> 16, 'o' -> 15,
  't' -> 15, 'n' -> 15, 's' -> 14, 'l' -> 13, 'c' -> 12,
  'u' -> 11, 'd' -> 10, 'p' -> 10, 'm' -> 10, 'h' -> 10,
    'g' -> 9, 'b' -> 8, 'f' -> 8, 'y' -> 8, 'w' -> 6,
  'k' -> 6, 'v' -> 6, 'x' -> 5, 'z' -> 5, 'j' -> 5, 'q' -> 5
  )


  def checkLetter(letter: Char): Boolean = {
    if (!('a' <= letter && letter <= 'z')){
      println("o harf bizde yok")
      return false
    }

    for (i <-  0  until moveList.length){
      if (letter.equals(moveList(i).getLetter())){
        println("harfi kullanmisin kanka ya")
        return false
      }
    }
    return true
  }

  def checkCard(card: Card): Boolean = {
    if (card.getCount() < 1){
      println("sende o karttan kalmamis")
      return false
    }
    if (card.getCost() > points){
      println("o karta puanin yetmiyo")
      return false
    }
    true
  }

  def reportStatus(): Unit = {
    word.printWord()
    println("points: " + points)
    if(finished()){
      if(word.allRevealed())
        println("kazandin")
      else{
        print("bulamadin kelime buydu: ")
        word.reveal()
      }
    }

  }




  def playMove(move: Move): Unit = {
    if(!finished()){
      moveList = move :: moveList
      applyMove()
      reportStatus()
    }
    else
      println("oyun bitmis")
  }

  def guess(discount: Double): Unit = {
    if(word.exist(moveList.head.getLetter())){
      moveList.head.setResult(true)
      println("aha buldun bi tane")
    }
    else{
      val pointDecrease: Int = (letterCosts(moveList.head.getLetter()) * (1 - discount)).toInt
      points = points - pointDecrease
      moveList.head.setResult(false)
      println("yok cikmadi")
    }
  }


  def applyMove(): Unit = {
    val currentCard = moveList.head.getCard()
    currentCard.play()
    points = points - currentCard.getCost()

    if(currentCard.getRevealsCategory()){
      word.revealCategory()
    }
    if (currentCard.getOpensLetter()){
      word.openLetter(2)
    }
    if (currentCard.getMakesGuess()){
      if (currentCard.getGuessDiscount() != 0){
        guess(currentCard.getGuessDiscount())
      }
      else{
        if(moveList.length > 1)
          guess(moveList(1).calculateDiscountForNextMove())
        else
          guess(0)
      }
    }
  }

  def finished(): Boolean = {
    if(word.allRevealed() || points < 0)
      return true
    else
      return false
  }



}
