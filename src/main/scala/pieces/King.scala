package net.whg.manager
package pieces

class King extends Piece {
  override def doMove(moveFrom: (Int, Int), moveTo: (Int, Int)): MoveResult = ???

  override def init(): Boolean = ???

  override def getPos(): (Int, Int) = ???

  override def checkCheck(): Boolean = ???
}
