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


  def checkIndex(index: Int): Boolean = {
    if (index > 0){
      if (index >= word.word.length){
        println("out of bounds")
        return false
      }
      else if (word.indexIsOpen(index)){
        println("that place is already revealed")
        return false
      }
    }
    return true
  }




  def checkLetter(letter: Char): Boolean = {
    if (letter == '*'){
      return true
    }

    if (!('a' <= letter && letter <= 'z')){
      println("unknown letter has entered")
      return false
    }

    for (move <- moveList){
      if (letter.equals(move.usedLetter)){
        println("letter is already used")
        return false
      }
    }
    return true
  }




  def checkCard(card: Card): Boolean = {
    if (card.count < 1){
      println("you can not use thet card anymore")
      return false
    }
    if (card.cost > points){
      println("not enough points for the card")
      return false
    }
    true
  }



  def reportStatus(): Unit = {
    word.printWord()
    println("points: " + points)
    if(finished()){
      if(word.allRevealed())
        println("you won!!")
      else{
        print("you have lost secret word was: ")
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
    if (moveList.head.usedLetter.isDefined){
      if(word.exist(moveList.head.usedLetter.get)){
        moveList.head.setResult(true)
        println("you found one!!")
      }
      else{
        val pointDecrease: Int = (letterCosts(moveList.head.usedLetter.get) * (1 - discount)).toInt
        points = points - pointDecrease
        moveList.head.setResult(false)
        println("secret word does not include that letter")
      }
    }
  }


  def applyMove(): Unit = {
    if (moveList.head.usedCard.isDefined){
      val currentCard = moveList.head.usedCard
      currentCard.get.play()
      points = points - currentCard.get.cost

      if(currentCard.get.revealsCategory){
        word.revealCategory()
      }
      if (currentCard.get.opensLetter){
        word.openLetter(moveList.head.index.get)
      }
      if (currentCard.get.makesGuess){
        if (currentCard.get.guessDiscount != 0){
          guess(currentCard.get.guessDiscount)
        }
        else{
          if(moveList.length > 1)
            guess(moveList(1).calculateDiscountForNextMove())
          else
            guess(0)
        }
      }
    }
    else{
      if(moveList.length > 1)
        guess(moveList(1).calculateDiscountForNextMove())
      else
        guess(0)
    }
  }

  def finished(): Boolean = {
    if(word.allRevealed() || points < 0)
      return true
    else
      return false
  }



}
