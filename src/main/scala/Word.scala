class Word {
  val word = "eagle"
  //var wordStatus = Array.fill(word.length)(false)
  val wordStatus = new Array[Boolean](word.length)
  //wordStatus.foreach (status => println(status))

  def printWord(): Unit = {
    var i = 0
    while(i < word.length){
      if (wordStatus(i) == true)
        print(word.charAt(i))
      else
        print("*")
      i = i + 1
    }
    println("")
  }

  def exist(letter: Char): Boolean = {
    var i = 0
    var result = false
    while(i < word.length){
      if (word.charAt(i).equals(letter)){
        wordStatus(i) = true
        result = true
      }
      i = i + 1
    }
    result
  }

  def openLetter(index: Int): Unit ={
    wordStatus(index) = true
  }
}
