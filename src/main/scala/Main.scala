object Main extends App{
  val aslan = new MoveMaker(new Game("normal"))
  aslan.makeMove("RISK", 'a')
  aslan.makeMove("NO_CARD", 'o')

}
